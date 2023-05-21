package org.team.voided.eridanus.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import org.team.voided.eridanus.id
import org.team.voided.eridanus.item.ItemGroups

object EridanusBlocks {
    val VOID_CRYSTAL_BLOCK: Block = regBlock(
        "void_crystal_block",
        Block(FabricBlockSettings.of(Material.AMETHYST).sounds(BlockSoundGroup.AMETHYST_BLOCK))
    )

    fun register() {
    }

    fun regBlock(path: String, block: Block): Block {
        val item = Registry.register(
            Registries.ITEM,
            id(path),
            BlockItem(block, FabricItemSettings())
        )
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.ERIDANUS_BLOCKS)
            .register(ItemGroupEvents.ModifyEntries { it.add(item) })
        return Registry.register(Registries.BLOCK, id(path), block)
    }
}
