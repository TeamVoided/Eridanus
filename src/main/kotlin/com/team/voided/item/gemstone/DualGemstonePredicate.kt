package com.team.voided.item.gemstone

import net.minecraft.entity.Entity

@FunctionalInterface
interface DualGemstonePredicate<T, V> {
    companion object {
        val TRUE: DualGemstonePredicate<Entity, Int> = object : DualGemstonePredicate<Entity, Int> {
            override fun calculate(param0: Entity, param1: Int): Boolean = true
        }
    }

    fun calculate(param0: T, param1: V): Boolean
}