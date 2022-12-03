package com.team.voided.item.gemstone

import com.team.voided.EridanusRegistries
import com.team.voided.identifierFromString
import com.team.voided.item.ItemNBTHelper
import net.minecraft.item.ItemStack
import java.nio.charset.StandardCharsets

class GemstoneHelper {
    companion object {
        fun getGemstone(type: GemstoneType, stack: ItemStack): Gemstone? {
            val gemstoneId = identifierFromString(String(ItemNBTHelper.getByteArray("eridanus.gemstone.${type.name.lowercase()}", stack), StandardCharsets.UTF_8))

            return EridanusRegistries.GEMSTONE[gemstoneId]
        }

        fun setGemstone(type: GemstoneType, stack: ItemStack, gemstone: Gemstone) {
            ItemNBTHelper.putByteArray("eridanus.gemstone.${type.name.lowercase()}", gemstone.id.toString().toByteArray(StandardCharsets.UTF_8), stack)
        }
    }
}