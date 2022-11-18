package com.team.voided.item

import com.team.voided.VoidRarity
import com.team.voided.id
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

class VoidShardItems {
    companion object {
        val COMMON = VoidShardItem(Item.Settings(), VoidRarity.COMMON)
        val RARE = VoidShardItem(Item.Settings(), VoidRarity.RARE)
        val CHAMPION = VoidShardItem(Item.Settings(), VoidRarity.CHAMPION)
        val LEGENDARY = VoidShardItem(Item.Settings(), VoidRarity.LEGENDARY)
        val MYTHICAL = VoidShardItem(Item.Settings(), VoidRarity.MYTHICAL)

        fun register() {
            Registry.register(Registry.ITEM, id("void_shard_common"), COMMON)
            Registry.register(Registry.ITEM, id("void_shard_rare"), RARE)
            Registry.register(Registry.ITEM, id("void_shard_champion"), CHAMPION)
            Registry.register(Registry.ITEM, id("void_shard_legendary"), LEGENDARY)
            Registry.register(Registry.ITEM, id("void_shard_mythical"), MYTHICAL)
        }
    }
}