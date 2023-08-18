package org.teamvoided.eridanus.remove

import net.minecraft.item.Item
import net.minecraft.util.Rarity

@Suppress("unused")
class VoidShardItem(settings: Settings, private val rarity: Rarity) : Item(settings) {
    fun getRarity(): Rarity = rarity
}