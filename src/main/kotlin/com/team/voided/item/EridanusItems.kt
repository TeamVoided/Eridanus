package com.team.voided.item

import com.team.voided.VoidRarity
import com.team.voided.id
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

class EridanusItems {
    companion object {
        val VOID_SHARD = VoidShardItem(Item.Settings(), VoidRarity.COMMON)
        val VOID_SHARD_POUCH = VoidShardPouch(Item.Settings())

        fun register() {
            Registry.register(Registry.ITEM, id("void_shard"), VOID_SHARD)
            Registry.register(Registry.ITEM, id("void_shard_pouch"), VOID_SHARD_POUCH)
        }
    }
}