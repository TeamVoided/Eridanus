package com.team.voided.dung

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vector4f
import net.minecraft.world.World

enum class Tile(
    private val block: Block,
    private val sides: Vector4f
) {
    NOTHING(Blocks.WHITE_CONCRETE, Vector4f(0F, 0F, 0F, 0F)),
    IRON(Blocks.IRON_BLOCK, Vector4f(1F, 1F, 0F, 1F)),
    NETHER(Blocks.NETHERITE_BLOCK, Vector4f(0F, 2F, 1F, 2F)),
    DEEP(Blocks.POLISHED_DEEPSLATE, Vector4f(0F, 1F, 0F, 2F)),
    COPPER(Blocks.OXIDIZED_CUT_COPPER, Vector4f(3F, 1F, 3F, 1F));

    fun place(world: World, pos: BlockPos) {
        world.setBlockState(pos, this.block.defaultState)
    }
    fun getSides(): Vector4f {
        return this.sides
    }
}