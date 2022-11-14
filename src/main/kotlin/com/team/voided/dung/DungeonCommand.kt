package com.team.voided.dung

import com.mojang.brigadier.context.CommandContext
import com.team.voided.lib.Vec2i
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import net.minecraft.util.math.Vec2f
import kotlin.random.Random

object DungeonCommand {
    val size = 4
    val directions = listOf(
        Vec2i(0, -1),
        Vec2i(-1, 0),
        Vec2i(1, 0),
        Vec2i(0, 1)
    )

    private var dungeon: Array<Array<UnknownTile>> = Array(size) { _ -> Array(size) { _ -> UnknownTile.FULL } }

    fun run(context: CommandContext<ServerCommandSource>): Int {
        if (context.source.entity!!.isPlayer) {
            val player = context.source.entity as PlayerEntity
            val pos = player.blockPos.mutableCopy()

            val sTile = dungeon[Random.nextInt(size)][Random.nextInt(size)]
            sTile.pStates = arrayOf(Tile.NOTHING)
            sTile.collapse()
//            propagatePStates()
            player.sendMessage(Text.literal("Done gen!"))
            return 1
        }
        return 0
    }

    private fun propagatePStates(inTile:Vec2i) {
    for (dir in directions){
        var newTile = inTile
        if (newTile.)
    }

    }
}