package org.teamvoided.eridanus.blocks

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.eridanus.Eridanus.id
import org.teamvoided.eridanus.item.EriTabs
import java.util.*


@Suppress("MemberVisibilityCanBePrivate")
object EriBlocks {
    val ERI_BLOCKS = LinkedList<Block>()

    // Blocks are made here
    val VOID_CRYSTAL_BLOCK: Block = Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK))
    val RANDOM_BLOCK: Block = Block(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK))

    fun init() {
        // Then you register them here
        blockWithItem("void_crystal_block", VOID_CRYSTAL_BLOCK)
        blockWithItem("random_block", RANDOM_BLOCK)


        // They are automatically added to the creative tab
        ItemGroupEvents.modifyEntriesEvent(EriTabs.ERIDANUS_BLOCKS)
            .register { entries -> entries.addAll(ERI_BLOCKS.map { it.asItem().defaultStack }) }
    }

    private fun blockWithItem(id: String, block: Block): Block {
        ERI_BLOCKS.add(block)
        Registry.register(Registries.ITEM, id(id), BlockItem(block, FabricItemSettings()))
        return register(id, block)
    }
    private fun register(id: String, block: Block): Block = Registry.register(Registries.BLOCK, id(id), block)
}
