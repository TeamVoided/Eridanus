package org.team.voided.eridanus

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.team.voided.eridanus.config.ClientConfig
import org.teamvoided.voidlib.config.ServerConfig
import org.team.voided.eridanus.item.EridanusItems
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.team.voided.eridanus.block.EridanusBlocks

const val MODID = "eridanus"

@JvmField val LOGGER: Logger = LoggerFactory.getLogger(MODID)
@JvmField val GSON: Gson? = GsonBuilder().setPrettyPrinting().create()
@JvmField val clientConfig: ClientConfig = ClientConfig(id("eridanus_client_config")).withName(Text.translatable("config.client.eridanus"))
@JvmField val serverConfig: ServerConfig = ServerConfig(id("eridanus_server_config")).addServerStartupHook().addServerShutdownHook()

@Suppress("unused")
fun onInitialize() {
    LOGGER.info("Hello empty void!")

    EridanusItems.register()
    EridanusBlocks.register()
}

fun id(path: String): Identifier = Identifier(MODID, path)

fun identifierFromString(string: String): Identifier {
    if (string.contains(":")) {
        val split = string.split(":")
        return Identifier(split[0], split[1])
    }

    return Identifier(string)
}
