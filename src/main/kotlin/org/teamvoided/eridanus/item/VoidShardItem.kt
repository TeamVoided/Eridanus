package org.teamvoided.eridanus.item

import org.teamvoided.eridanus.VoidRarity
import net.minecraft.item.Item
@Suppress("unused")
class VoidShardItem(settings: Settings?, rarityArg: VoidRarity) : Item(settings) {
    private val rarity: VoidRarity = rarityArg

    fun getRarity(): VoidRarity = rarity
}