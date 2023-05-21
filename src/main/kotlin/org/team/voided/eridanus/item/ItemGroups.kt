package org.team.voided.eridanus.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import org.team.voided.eridanus.id

object ItemGroups {
    val ERIDANUS_ITEMS: ItemGroup = FabricItemGroup.builder(id("eridanus_items"))
        .icon { ItemStack(EridanusItems.STAR) }
        .build()

    val ERIDANUS_BLOCKS: ItemGroup = FabricItemGroup.builder(id("eridanus_blocks"))
        .icon { ItemStack(EridanusItems.STAR) }
        .build()
}