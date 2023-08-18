package org.teamvoided.eridanus.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import org.teamvoided.eridanus.Eridanus.id
import org.teamvoided.eridanus.block.EridanusBlocks

object ItemGroups {
    val ERIDANUS_ITEMS = register("eridanus_items")



    val ERIDANUS_BLOCKS = register("eridanus_blocks")
    fun init() {
        Registry.register(
            Registries.ITEM_GROUP,
            ERIDANUS_ITEMS,
            FabricItemGroup.builder()
                .icon { EridanusItems.STAR.defaultStack }
                .displayName(Text.of("Eridanus Items"))
               .build()
        )

        Registry.register(
            Registries.ITEM_GROUP,
            ERIDANUS_BLOCKS,
            FabricItemGroup.builder()
                .icon { EridanusBlocks.VOID_CRYSTAL_BLOCK.asItem().defaultStack }
                .displayName(Text.of("Eridanus Blocks"))
                .build()
        )

    }


    private fun register(name: String): RegistryKey<ItemGroup> = RegistryKey.of(RegistryKeys.ITEM_GROUP, id(name))
}