package org.team.voided.eridanus.item

import org.team.voided.eridanus.VoidRarity
import net.minecraft.item.Item

class VoidShardItem(settings: Settings?, rarityArg: VoidRarity) : Item(settings) {
    private val rarity: VoidRarity = rarityArg

    fun getRarity(): VoidRarity = rarity
}