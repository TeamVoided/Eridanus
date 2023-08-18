package org.teamvoided.eridanus.item.gemstone

import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot
import net.minecraft.util.ClickType

open class GemstoneItem(settings: Settings, val gemstone: Gemstone) : Item(settings.maxCount(1)) {

    override fun onStackClicked(stack: ItemStack, slot: Slot, clickType: ClickType, player: PlayerEntity): Boolean {
        if (!slot.stack.isEmpty) {
            if (gemstone.slot.acceptApplyOn(slot.stack) && gemstone.acceptApplyOn(slot.stack)) {
                val oGemstone = GemstoneHelper.getGemstone(gemstone.slot, slot.stack)
                if (oGemstone == null) {
                    GemstoneHelper.setGemstone(gemstone.slot, slot.stack, gemstone)
                    gemstone.applyAttributeMods(slot.stack)
                    stack.decrement(1)
                } else {
                    val original = slot.stack.copy()
                    slot.stack = ItemStack(slot.stack.item, slot.stack.count)
                    EnchantmentHelper.get(original).forEach { (enchantment, level) ->
                        slot.stack.addEnchantment(enchantment, level)
                    }
                    slot.stack.setCustomName(original.name)
                    slot.stack.damage = original.damage
                    slot.stack.repairCost = original.repairCost
                    slot.stack.bobbingAnimationTime = original.bobbingAnimationTime
                    GemstoneHelper.setGemstone(gemstone.slot, slot.stack, gemstone)
                    gemstone.applyAttributeMods(slot.stack)
                    stack.decrement(1)
                }
                return true
            }
        }

        return false
    }
}