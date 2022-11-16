package com.team.voided.dung

import com.mojang.brigadier.context.CommandContext
import com.team.voided.GSON
import com.team.voided.LOGGER
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import kotlin.random.Random

object DungeonCommand {
    private const val size = 4
    /*
    private val directions = listOf(
    Vec2i(0, -1),
    Vec2i(-1, 0),
    Vec2i(1, 0),
    Vec2i(0, 1)
    )
    */

    private var dungeon: Array<Array<UnknownTile>> = Array(size) { Array(size) { UnknownTile.FULL.copy() } }

    fun run(context: CommandContext<ServerCommandSource>): Int {
        if (context.source.entity!!.isPlayer) {
            val player = context.source.entity as PlayerEntity
            /* val pos = player.blockPos.mutableCopy() */
            val sTile: UnknownTile = dungeon[Random.nextInt(size)][Random.nextInt(size)]
            sTile.pStates = arrayOf(Tile.NOTHING)
            sTile.collapse()

            /*
            val list: Array<UnknownTile> = dungeon.clone().reduce { acc, tiles -> acc + tiles }
            list.sortBy { tile -> tile.entropyLevel() }
            */
            for (ray in dungeon) {
                for (item in ray) {
                    LOGGER.info(GSON!!.toJson(item).toString() + "\n" + item.entropyLevel())
                }
            }
            player.sendMessage(Text.literal("Done gen!"))
            return 1
        }
        return 0
    }

    /*
     private fun propagatePStates(inTile: Vec2i) {
         for (dir in directions) {
             val newTile = inTile.copy().add(dir)
             if (
                 newTile.x in size-1 downTo 0 &&
                 newTile.y in size-1 downTo 0
             ) {
                 LOGGER.info("Old - ${inTile.x}:${inTile.y} | dir -${dir.x}:${dir.y} | new ${newTile.x}:${newTile.y}")
                 LOGGER.info("this is :${dungeon[newTile.x][newTile.y].isCollapsed()}")
                 if (!dungeon[newTile.x][newTile.y].isCollapsed()
                 ) {
                     dungeon[newTile.x][newTile.y].collapse()
                     propagatePStates(newTile)
                 }
             }
         }
     }
     */
}