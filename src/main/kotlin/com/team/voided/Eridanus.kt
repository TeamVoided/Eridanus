package com.team.voided

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mojang.brigadier.tree.LiteralCommandNode
import com.team.voided.config.ClientConfig
import com.team.voided.config.ServerConfig
import com.team.voided.dung.DungeonCommand
import com.team.voided.item.VoidShardItems
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

const val MODID = "eridanus"

val id: (String) -> Identifier = { Identifier(MODID, it) }

@JvmField val LOGGER: Logger = LoggerFactory.getLogger(MODID)
@JvmField val GSON: Gson? = GsonBuilder().setPrettyPrinting().create()
@JvmField val clientConfig: ClientConfig = ClientConfig(id("eridanus_client_config")).withName(Text.translatable("config.client.eridanus"))
@JvmField val serverConfig: ServerConfig = ServerConfig(id("eridanus_server_config")).addServerStartupHook().addServerShutdownHook()

@Suppress("unused")
fun onInitialize() {
    LOGGER.info("Hello empty void!")

    CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
        val dungeonNode: LiteralCommandNode<ServerCommandSource> = CommandManager
            .literal("dungeon")
            .executes(DungeonCommand::run)
            .build()
        dispatcher.root.addChild(dungeonNode)
    }

    VoidShardItems.register()
}
