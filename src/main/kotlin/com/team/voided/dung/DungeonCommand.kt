package com.team.voided.dung

import com.mojang.brigadier.context.CommandContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import kotlin.random.Random

object DungeonCommand {
    val size = 4

    private var dungeon = Array(size) { _ -> Array(size) { _ -> UnknownTile.FULL } }

    fun run(context: CommandContext<ServerCommandSource>): Int {
        if (context.source.entity!!.isPlayer) {
            val player = context.source.entity as PlayerEntity
            val pos = player.blockPos.mutableCopy()
            val sTile = dungeon[Random.nextInt(size)][Random.nextInt(size)]
            sTile.pStates = arrayOf(Tile.NOTHING)
            sTile.collapse()

            player.sendMessage(Text.literal("Done gen!"))
            return 1
        }
        return 0
    }
}