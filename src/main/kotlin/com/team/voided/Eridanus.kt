package com.team.voided

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.team.voided.config.ClientConfig
import com.team.voided.config.ServerConfig
import com.team.voided.entity.EridanusEntities
import com.team.voided.item.EridanusItems
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

const val MODID = "eridanus"

@JvmField val LOGGER: Logger = LoggerFactory.getLogger(MODID)
@JvmField val GSON: Gson? = GsonBuilder().setPrettyPrinting().create()
@JvmField val clientConfig: ClientConfig = ClientConfig(id("eridanus_client_config")).withName(Text.translatable("config.client.eridanus"))
@JvmField val serverConfig: ServerConfig = ServerConfig(id("eridanus_server_config")).addServerStartupHook().addServerShutdownHook()

@Suppress("unused")
fun onInitialize() {
    LOGGER.info("Hello empty void!")

    EridanusItems.register()
    EridanusEntities.registerAttributes()
}

fun id(path: String): Identifier
    = Identifier(MODID, path)

fun identifierFromString(string: String): Identifier {
    if (string.contains(":")) {
        val split = string.split(":")
        return Identifier(split[0], split[1])
    }

    return Identifier(string)
}
