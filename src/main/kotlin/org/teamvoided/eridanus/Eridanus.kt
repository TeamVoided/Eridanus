package org.teamvoided.eridanus

import org.teamvoided.eridanus.item.EriItems
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.eridanus.blocks.EriBlocks
import org.teamvoided.eridanus.client.Keybinds
import org.teamvoided.eridanus.item.EriTabs


@Suppress("unused")
object Eridanus {
    const val MODID = "eridanus"
    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MODID)



    fun commonInit() {
        LOGGER.info("Hello empty void!")
        EriItems.init()
        EriBlocks.init()
        EriTabs.init()
    }

    fun clientInit() {
        LOGGER.info("Yo mother it is I! The Client")
        Keybinds.init()
    }

    fun id(path: String) = Identifier(MODID, path)
    fun mc(path: String) = Identifier(path)

    fun identifierFromString(string: String): Identifier {
        if (string.contains(":")) {
            val split = string.split(":")
            return Identifier(split[0], split[1])
        }
        return Identifier(string)
    }
}
