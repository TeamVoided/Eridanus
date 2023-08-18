package org.teamvoided.eridanus.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.eridanus.Eridanus.id
import org.teamvoided.eridanus.item.ItemGroups


@Suppress("MemberVisibilityCanBePrivate")
object EridanusBlocks {
    val VOID_CRYSTAL_BLOCK: Block = Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK))

    fun init() {
        blockWithItem("void_crystal_block", VOID_CRYSTAL_BLOCK)
    }

    private fun blockWithItem(id: String, block: Block): Block {
        val item = Registry.register(Registries.ITEM, id(id), BlockItem(block, FabricItemSettings()))
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.ERIDANUS_BLOCKS)
            .register(ItemGroupEvents.ModifyEntries { it.add(item) })
        return Registry.register(Registries.BLOCK, id(id), block)
    }
}
