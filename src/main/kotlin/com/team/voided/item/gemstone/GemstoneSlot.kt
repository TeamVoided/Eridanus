package com.team.voided.item.gemstone

import com.team.voided.EridanusRegistries
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

open class GemstoneSlot(val id: Identifier) {
    private var applyPredicate: GemstonePredicate<ItemStack> = GemstonePredicate.TRUE

    fun acceptApplyOn(stack: ItemStack): Boolean {
        return applyPredicate.calculate(stack)
    }

    class Builder(val id: Identifier) {
        private var applyPredicate: GemstonePredicate<ItemStack> = GemstonePredicate.TRUE

        fun withApplyPredicate(applyPredicate: GemstonePredicate<ItemStack>): Builder {
            this.applyPredicate = applyPredicate
            return this
        }

        fun build(): GemstoneSlot {
            val slot = GemstoneSlot(id)
            slot.applyPredicate = applyPredicate

            return slot
        }

        fun buildAndRegister(): GemstoneSlot {
            val slot = build()
            return Registry.register(EridanusRegistries.GEMSTONE_SLOT, id, slot)
        }
    }
}