package org.teamvoided.eridanus

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.teamvoided.eridanus.item.EridanusItems
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.eridanus.block.EridanusBlocks
import org.teamvoided.eridanus.item.ItemGroups


@Suppress("unused")
object Eridanus {

    const val MODID = "eridanus"
    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MODID)
    val GSON: Gson = GsonBuilder().setPrettyPrinting().create()
//    val serverConfig: ServerConfig =
//        ServerConfig(id("eridanus_server_config")).addServerStartupHook().addServerShutdownHook()
    //@JvmField val clientConfig: ClientConfig = ClientConfig(id("eridanus_client_config")).withName(Text.translatable("config.client.eridanus"))
    fun onInitialize() {
        LOGGER.info("Hello empty void!")
        EridanusItems.init()
        EridanusBlocks.init()
        ItemGroups.init()
    }

    fun id(path: String): Identifier = Identifier(MODID, path)

    fun identifierFromString(string: String): Identifier {
        if (string.contains(":")) {
            val split = string.split(":")
            return Identifier(split[0], split[1])
        }

        return Identifier(string)
    }
}
