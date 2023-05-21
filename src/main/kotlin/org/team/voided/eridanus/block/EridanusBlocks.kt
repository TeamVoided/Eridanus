package org.team.voided.eridanus.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import org.team.voided.eridanus.id

object EridanusBlocks {
    val VOID_CRYSTAL_BLOCK = Block(FabricBlockSettings.of(Material.AMETHYST).sounds(BlockSoundGroup.AMETHYST_BLOCK))
    fun register(){
        regBlock("void_crystal_block", VOID_CRYSTAL_BLOCK)
    }
    fun regBlock(path:String, block: Block) {
        Registry.register(Registries.BLOCK, id(path), block)
        Registry.register(Registries.ITEM, id(path), BlockItem(block, FabricItemSettings()))
    }
}
