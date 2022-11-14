package com.team.voided

import com.mojang.brigadier.tree.LiteralCommandNode
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory


const val MODID = "eridanus"

@JvmField
val LOGGER: Logger = LoggerFactory.getLogger(MODID)

@Suppress("unused")
fun onInitialize() {

    LOGGER.info("Hello Fabric world!")
    CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
        val dungeonNode: LiteralCommandNode<ServerCommandSource> = CommandManager
            .literal("dungeon")
//            .executes(DungeonCommand::run)
            .build()
        dispatcher.root.addChild(dungeonNode)
    }
}
