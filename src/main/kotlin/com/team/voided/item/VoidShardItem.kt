package com.team.voided.item

import com.team.voided.VoidRarity
import net.minecraft.item.Item

class VoidShardItem(settings: Settings?, rarityArg: VoidRarity) : Item(settings) {
    private val rarity: VoidRarity = rarityArg

    fun getRarity(): VoidRarity = rarity
}