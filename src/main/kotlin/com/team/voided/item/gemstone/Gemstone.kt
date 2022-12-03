package com.team.voided.item.gemstone

import com.team.voided.EridanusRegistries
import com.team.voided.LOGGER
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

class Gemstone(val id: Identifier) {
    private var attributeMods: MutableMap<EntityAttribute, Pair<EquipmentSlot, Pair<Operation, Double>>> = HashMap()
    private var miningSpeedModifier: Pair<Operation, Float> = Pair(ADDITION, 0f)
    private var statusEffects: List<Pair<StatusEffect, Int>> = LinkedList()

    fun applyAttributeMods(stack: ItemStack) {
        attributeMods.forEach { (attribute, pair) ->
            stack.addAttributeModifier(attribute, EntityAttributeModifier("eridanus.gemstone.modifier.${attribute.translationKey}", pair.second.second, pair.second.first), pair.first)
        }
    }

    fun applyStatusEffects(entity: Entity?) {
        LOGGER.info("Applying stage 1")

        if (entity is LivingEntity) {
            statusEffects.forEach {
                entity.addStatusEffect(StatusEffectInstance(it.first, 20, it.second, false, true))
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

    class Builder(val id: Identifier) {
        private val attributeMods: MutableMap<EntityAttribute, Pair<EquipmentSlot, Pair<Operation, Double>>> = HashMap()
        private var miningSpeedModifier: Pair<Operation, Float> = Pair(ADDITION, 0f)
        private val statusEffects: MutableList<Pair<StatusEffect, Int>> = LinkedList()

        fun modifyAttribute(attribute: EntityAttribute, operation: Operation, modifier: Double, equipmentSlot: EquipmentSlot): Builder {
            attributeMods[attribute] = Pair(equipmentSlot, Pair(operation, modifier))
            return this
        }

        fun modifyAttribute(attribute: EntityAttribute, attributeModifier: EntityAttributeModifier, equipmentSlot: EquipmentSlot): Builder {
            attributeMods[attribute] = Pair(equipmentSlot, Pair(attributeModifier.operation, attributeModifier.value))
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

        fun addStatusEffect(statusEffect: StatusEffect, level: Int): Builder {
            statusEffects.add(Pair(statusEffect, level))
            return this
        }

        fun build(): Gemstone {
            val gemstone = Gemstone(id)
            gemstone.attributeMods = attributeMods
            gemstone.miningSpeedModifier = miningSpeedModifier
            gemstone.statusEffects = statusEffects

            return gemstone
        }

        fun buildAndRegister(): Gemstone {
            val gemstone = build()

            return Registry.register(EridanusRegistries.GEMSTONE, gemstone.id, gemstone)
        }
    }
}