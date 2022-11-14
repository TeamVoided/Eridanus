package com.team.voided.dung

import com.mojang.brigadier.context.CommandContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

object DungeonCommand {

    private var dungeon = Array(4) { _ -> Array(4) { _ -> UnknownTile.FULL } }

    fun run(context: CommandContext<ServerCommandSource>): Int {
//        LOGGER.info("hi$context")
        if (context.source.entity!!.isPlayer) {
            val player = context.source.entity as PlayerEntity
            player.sendMessage(Text.literal("Starting gen.."))
            val pos = player.blockPos.mutableCopy()
            pos.y--
            for (i in dungeon.indices) {
                for (j in 0 until dungeon[i].size) {
                    val modifiedPos = pos.mutableCopy()
                    modifiedPos.x += i
                    modifiedPos.z += j
                }
            }
            player.sendMessage(Text.literal("Done gen!"))
            return 1
        }
        return 0
    }
}