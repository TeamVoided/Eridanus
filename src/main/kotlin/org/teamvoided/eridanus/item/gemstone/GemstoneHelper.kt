package org.teamvoided.eridanus.item.gemstone

import org.teamvoided.eridanus.EridanusRegistries
import org.teamvoided.eridanus.Eridanus.identifierFromString
import org.teamvoided.eridanus.item.ItemNBTHelper
import net.minecraft.item.ItemStack
import java.nio.charset.StandardCharsets

class GemstoneHelper {
    companion object {
        fun getGemstone(slot: GemstoneSlot, stack: ItemStack): Gemstone? {
            val gemstoneId = identifierFromString(String(ItemNBTHelper.getByteArray("eridanus.gemstone.${slot.id.namespace}.${slot.id.path}", stack), StandardCharsets.UTF_8))

            return EridanusRegistries.GEMSTONE[gemstoneId]
        }

        fun setGemstone(slot: GemstoneSlot, stack: ItemStack, gemstone: Gemstone) {
            ItemNBTHelper.putByteArray("eridanus.gemstone.${slot.id.namespace}.${slot.id.path}", gemstone.id.toString().toByteArray(StandardCharsets.UTF_8), stack)
        }
    }
}