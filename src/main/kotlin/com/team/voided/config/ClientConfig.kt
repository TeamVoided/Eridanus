package com.team.voided.config

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.team.voided.LOGGER
import com.team.voided.config.controllers.CharInputController
import com.team.voided.config.controllers.NumberInputController
import dev.isxander.yacl.api.ConfigCategory
import dev.isxander.yacl.api.Option
import dev.isxander.yacl.api.YetAnotherConfigLib
import dev.isxander.yacl.gui.controllers.BooleanController
import dev.isxander.yacl.gui.controllers.string.StringController
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Files
import kotlin.io.path.Path

@Suppress("duplicates")
class ClientConfig(_id: Identifier) : Config<ClientConfig>(_id) {
    private var categoryBuilder: ConfigCategory.Builder? = null
    private var name: Text? = null

    companion object {
        fun fromJson(id: Identifier): ClientConfig? {
            val config = ClientConfig(id)

            val file: File = Path("config/client/${id.namespace}/${id.path}.json").toFile()

            if (Files.exists(file.toPath())) {
                val reader = FileReader(file)

                val json: JsonObject = reader.use {
                    Gson().fromJson(it, JsonObject().javaClass)
                }

                json.entrySet().forEach { entry ->
                    config.setValue(entry.key, ConfigValue.fromJson(entry.value.asJsonObject)!!)
                }

                return config
            }

            return null
        }
    }

    fun withName(name: Text): ClientConfig {
        this.name = name
        return this
    }

    fun withCategoryBuilder(builder: ConfigCategory.Builder): ClientConfig {
        this.categoryBuilder = builder
        return this
    }

    override fun setValue(id: String, value: ConfigValue): ClientConfig {
        values[id] = value
        return this
    }

    override fun removeValue(id: String): ClientConfig {
        values.remove(id)
        return this
    }

    override fun save(): ClientConfig {
        LOGGER.info("Saving config $id")
        val json = JsonObject()
        val file: File = Path("config/server/${id.namespace}/${id.path}.json").toFile()

        if (Files.notExists(file.toPath())) {
            Files.createDirectories(file.parentFile.toPath())
            Files.createFile(file.toPath())
        }

        values.forEach { (id, value) ->
            json.add(id, value.toJson())
        }

        FileWriter(file).use {
            it.write(Gson().toJson(json))
        }

        return this
    }

    override fun load(): ClientConfig {
        val other: ClientConfig? = fromJson(id)
        if (other == null) {
            LOGGER.info("Nothing to load for $id")
            return this
        }

        LOGGER.info("Loading config from $id...")

        values.forEach { (str, _) ->
            values.remove(str)
        }

        values.putAll(other.values)

        return this
    }

    fun createGui(parent: Screen): Screen {
        val builder = YetAnotherConfigLib.createBuilder()
        builder.title(name ?: Text.translatable("config.name.${id.namespace}.${id.path.replace("/", ".")}"))
        builder.category(createOptions(categoryBuilder ?: ConfigCategory.createBuilder()).name(Text.translatable("config.category.${id.namespace}.${id.path.replace("/", ".")}")).build())
        builder.save(this::save)
        return builder.build().generateScreen(parent)
    }

    private fun createOptions(builder: ConfigCategory.Builder): ConfigCategory.Builder {
        values.forEach { (id, value) ->
            when(value.getConfigType()) {
                ConfigValue.ConfigType.NUMBER -> {
                    builder.option(
                        Option.createBuilder(Number::class.java)
                        .name(value.getName() ?: Text.of(id))
                        .tooltip(value.getTooltip() ?: Text.empty())
                        .binding(
                            value.getDefNumberVal(),
                            { value.getNumberVal() },
                            { new -> value.setNumberVal(new) }
                        ).controller { option -> NumberInputController(option) }
                        .build())
                }
                ConfigValue.ConfigType.STRING -> {
                    builder.option(
                        Option.createBuilder(String::class.java)
                        .name(value.getName() ?: Text.of(id))
                        .tooltip(value.getTooltip() ?: Text.empty())
                        .binding(
                            value.getDefStringVal(),
                            { value.getStringVal() },
                            { new -> value.setStringVal(new) }
                        ).controller { option -> StringController(option) }
                        .build())
                }
                ConfigValue.ConfigType.BOOLEAN -> {
                    builder.option(
                        Option.createBuilder(Boolean::class.java)
                        .name(value.getName() ?: Text.of(id))
                        .tooltip(value.getTooltip() ?: Text.empty())
                        .binding(
                            value.getDefBooleanVal(),
                            { value.getBooleanVal() },
                            { new -> value.setBooleanVal(new) }
                        ).controller { option -> BooleanController(option) }
                        .build())
                }
                ConfigValue.ConfigType.CHAR -> {
                    builder.option(
                        Option.createBuilder(Char::class.java)
                        .name(value.getName() ?: Text.of(id))
                        .tooltip(value.getTooltip() ?: Text.empty())
                        .binding(
                            value.getDefCharVal(),
                            { value.getCharVal() },
                            { new -> value.setCharVal(new) }
                        ).controller { option -> CharInputController(option) }
                        .build())
                }
                else -> {}
            }
        }

        return builder
    }
}