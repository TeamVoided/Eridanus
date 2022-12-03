package com.team.voided.item.gemstone

import com.team.voided.EridanusRegistries
import com.team.voided.identifierFromString
import com.team.voided.item.ItemNBTHelper
import net.minecraft.item.ItemStack
import java.nio.charset.StandardCharsets

class GemstoneHelper {
    companion object {
        fun getGemstone(stack: ItemStack): Gemstone? {
            val gemstoneId = identifierFromString(String(ItemNBTHelper.getByteArray("eridanus.gemstone", stack), StandardCharsets.UTF_8))

            return EridanusRegistries.GEMSTONE[gemstoneId]
        }

        fun setGemstone(stack: ItemStack, gemstone: Gemstone) {
            ItemNBTHelper.putByteArray("eridanus.gemstone", gemstone.id.toString().toByteArray(StandardCharsets.UTF_8), stack)
        }
    }
}