package com.team.voided.item.gemstone

import com.team.voided.EridanusRegistries
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation.*
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.util.*

open class Gemstone(val id: Identifier, val type: GemstoneType) {
    private var attributeMods: MutableMap<EntityAttribute, Pair<Pair<EquipmentSlot, Pair<Operation, Double>>, GemstonePredicate<ItemStack>>> = HashMap()
    private var miningSpeedModifier: Pair<Operation, Float> = Pair(ADDITION, 0f)
    private var statusEffects: List<Pair<Pair<StatusEffect, Int>, DualGemstonePredicate<Entity, Int>>> = LinkedList()
    private var applyPredicate: GemstonePredicate<ItemStack> = GemstonePredicate.TRUE

    fun acceptApplyOn(toApply: ItemStack): Boolean {
        return applyPredicate.calculate(toApply)
    }

    fun applyAttributeMods(stack: ItemStack) {
        attributeMods.forEach { (attribute, pair) ->
            if (pair.second.calculate(stack)) {
                stack.addAttributeModifier(
                    attribute,
                    EntityAttributeModifier("eridanus.gemstone.modifier.${attribute.translationKey}.${System.nanoTime()}", pair.first.second.second, pair.first.second.first),
                    pair.first.first
                )
            }
        }
    }

    fun applyStatusEffects(entity: Entity, slot: Int) {
        if (entity is LivingEntity) {
            statusEffects.forEach {
                if (it.second.calculate(entity, slot)) {
                    entity.addStatusEffect(StatusEffectInstance(it.first.first, 20, it.first.second, false, true))
                }
            }
        }
    }

    fun calculateMiningSpeedModifier(base: Float): Float {
        return when (miningSpeedModifier.first) {
            ADDITION -> {
                base + miningSpeedModifier.second
            }

            MULTIPLY_BASE, MULTIPLY_TOTAL -> {
                base * miningSpeedModifier.second
            }
        }
    }

    class Builder(val id: Identifier, val type: GemstoneType) {
        private val attributeMods: MutableMap<EntityAttribute, Pair<Pair<EquipmentSlot, Pair<Operation, Double>>, GemstonePredicate<ItemStack>>> = HashMap()
        private var miningSpeedModifier: Pair<Operation, Float> = Pair(ADDITION, 0f)
        private val statusEffects: MutableList<Pair<Pair<StatusEffect, Int>, DualGemstonePredicate<Entity, Int>>> = LinkedList()
        private var applyPredicate: GemstonePredicate<ItemStack> = GemstonePredicate.TRUE

        fun modifyAttribute(attribute: EntityAttribute, operation: Operation, modifier: Double, equipmentSlot: EquipmentSlot, predicate: GemstonePredicate<ItemStack>): Builder {
            attributeMods[attribute] = Pair(Pair(equipmentSlot, Pair(operation, modifier)), predicate)
            return this
        }

        fun modifyAttribute(attribute: EntityAttribute, attributeModifier: EntityAttributeModifier, equipmentSlot: EquipmentSlot, predicate: GemstonePredicate<ItemStack>): Builder {
            attributeMods[attribute] = Pair(Pair(equipmentSlot, Pair(attributeModifier.operation, attributeModifier.value)), predicate)
            return this
        }

        fun setMiningSpeedModifier(operation: Operation, modifier: Float): Builder {
            miningSpeedModifier = Pair(operation, modifier)
            return this
        }

        fun setMiningSpeedModifierOperation(operation: Operation): Builder {
            miningSpeedModifier = Pair(operation, miningSpeedModifier.second)
            return this
        }

        fun setMiningSpeedModifierValue(modifier: Float): Builder {
            miningSpeedModifier = Pair(miningSpeedModifier.first, modifier)
            return this
        }

        fun addStatusEffect(statusEffect: StatusEffect, level: Int, predicate: DualGemstonePredicate<Entity, Int>): Builder {
            statusEffects.add(Pair(Pair(statusEffect, level), predicate))
            return this
        }

        fun withApplyPredicate(predicate: GemstonePredicate<ItemStack>): Builder {
            applyPredicate = predicate
            return this
        }

        fun build(): Gemstone {
            val gemstone = Gemstone(id, type)
            gemstone.attributeMods = attributeMods
            gemstone.miningSpeedModifier = miningSpeedModifier
            gemstone.statusEffects = statusEffects
            gemstone.applyPredicate = applyPredicate

            return gemstone
        }

        fun buildAndRegister(): Gemstone {
            val gemstone = build()

            return Registry.register(EridanusRegistries.GEMSTONE, gemstone.id, gemstone)
        }
    }
}