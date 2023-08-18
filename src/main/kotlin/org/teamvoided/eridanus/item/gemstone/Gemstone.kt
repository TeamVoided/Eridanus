package org.teamvoided.eridanus.item.gemstone

import org.teamvoided.eridanus.EridanusRegistries
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation.*
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Item.Settings
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.world.World
import java.util.*

open class Gemstone(val id: Identifier, val slot: GemstoneSlot) {
    private var attributeMods: MutableMap<EntityAttribute, Pair<Pair<EquipmentSlot, Pair<Operation, Double>>, GemstonePredicate<ItemStack>>> = HashMap()
    private var miningSpeedModifier: Pair<Operation, Float> = Pair(ADDITION, 0f)
    private var statusEffects: List<Pair<Pair<StatusEffect, Int>, GemstoneStatusEffectPredicate>> = LinkedList()
    private var applyPredicate: GemstonePredicate<ItemStack> = GemstonePredicate.TRUE
    private var item: GemstoneItem? = null

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

    fun applyStatusEffects(world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (entity is LivingEntity) {
            statusEffects.forEach {
                if (it.second.calculate(world, entity, slot, selected)) {
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

    fun getItem(): GemstoneItem? = item
    @Suppress("unused")
    class Builder(val id: Identifier, val slot: GemstoneSlot) {
        private val attributeMods: MutableMap<EntityAttribute, Pair<Pair<EquipmentSlot, Pair<Operation, Double>>, GemstonePredicate<ItemStack>>> = HashMap()
        private var miningSpeedModifier: Pair<Operation, Float> = Pair(ADDITION, 0f)
        private val statusEffects: MutableList<Pair<Pair<StatusEffect, Int>, GemstoneStatusEffectPredicate>> = LinkedList()
        private var applyPredicate: GemstonePredicate<ItemStack> = GemstonePredicate.TRUE
        private var itemSettings: Settings? = null

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

        fun addStatusEffect(statusEffect: StatusEffect, level: Int, predicate: GemstoneStatusEffectPredicate): Builder {
            statusEffects.add(Pair(Pair(statusEffect, level), predicate))
            return this
        }

        fun withApplyPredicate(predicate: GemstonePredicate<ItemStack>): Builder {
            applyPredicate = predicate
            return this
        }

        fun withItem(settings: Settings): Builder {
            this.itemSettings = settings
            return this
        }

        fun build(): Gemstone {
            val gemstone = Gemstone(id, slot)
            gemstone.attributeMods = attributeMods
            gemstone.miningSpeedModifier = miningSpeedModifier
            gemstone.statusEffects = statusEffects
            gemstone.applyPredicate = applyPredicate

            if (itemSettings != null) {
                gemstone.item = GemstoneItem(itemSettings!!, gemstone)
            }

            return gemstone
        }

        fun buildAndRegister(): Gemstone {
            val gemstone = build()

            if (itemSettings != null) {
                gemstone.item = (Registry.register(Registries.ITEM, id, gemstone.item) as GemstoneItem)
            }

            return Registry.register(EridanusRegistries.GEMSTONE, gemstone.id, gemstone)
        }
    }
}