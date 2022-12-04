package org.team.voided.eridanus.item.gemstone

import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack

@FunctionalInterface
interface GemstonePredicate<T> {
    companion object {
        val TRUE: GemstonePredicate<ItemStack> = object : GemstonePredicate<ItemStack> {
            override fun calculate(param: ItemStack): Boolean = true
        }

        val IS_ARMOR: GemstonePredicate<ItemStack> = object : GemstonePredicate<ItemStack> {
            override fun calculate(param: ItemStack): Boolean = param.item is ArmorItem
        }
    }

    fun calculate(param: T): Boolean
}