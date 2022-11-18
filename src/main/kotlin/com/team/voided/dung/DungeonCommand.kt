package com.team.voided.dung

import com.mojang.brigadier.context.CommandContext
import com.team.voided.LOGGER
import com.team.voided.lib.Vec2i
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import kotlin.random.Random

object DungeonCommand {
    var kill = false
    private const val size = 4

    private val directions = listOf(
        Vec2i(0, -1),
        Vec2i(-1, 0),
        Vec2i(1, 0),
        Vec2i(0, 1)
    )


    private var dungeon: Array<Array<UnknownTile>> = Array(size) { Array(size) { UnknownTile.FULL.copy() } }

    fun run(context: CommandContext<ServerCommandSource>): Int {
        if (context.source.entity!!.isPlayer) {
            val player = context.source.entity as PlayerEntity
            /* val pos = player.blockPos.mutableCopy() */
            val sTile: UnknownTile = dungeon[Random.nextInt(size)][Random.nextInt(size)]
            sTile.pStates = arrayOf(Tile.NOTHING)
            sTile.collapse()
            val list: List<UnknownTile> = dungeon.clone().reduce { acc, tiles -> acc + tiles }.asList()

            while (!isComplete()) {
                list.sortedBy { tile -> tile.entropyLevel() }.filter { tile -> !tile.isCollapsed() }
                for (item in list) {
                    LOGGER.info("${item.isCollapsed()} !")
                }
                if (kill) {
                    break
                }
            }

            player.sendMessage(Text.literal("Done gen!"))
            return 1
        }
        return 0
    }

    private fun isComplete(): Boolean {
        var fullyCollapsed = true
        dungeon.forEach {
            it.forEach { tile ->
                if (!tile.isCollapsed()) {
                    fullyCollapsed = false
                }
            }
        }

        return fullyCollapsed
    }


    private fun propagatePStates(inTile: Vec2i) {
        directions.forEach { dir ->
            val newTile = inTile.copy() + (dir)
            if (
                newTile.x in size - 1 downTo 0 &&
                newTile.y in size - 1 downTo 0
            ) {
                if (!dungeon[newTile.x][newTile.y].isCollapsed()
                ) {
                    dungeon[newTile.x][newTile.y].collapse()
                    propagatePStates(newTile)
                }
            }
        }
    }

}