package org.teamvoided.eridanus.item.gemstone

import com.google.common.collect.Maps
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.util.Util
import net.minecraft.world.World

@Suppress("unused")
@FunctionalInterface
interface GemstoneStatusEffectPredicate {
    companion object {
        private val SLOT_NAMES: Map<String, Int> = Util.make(
            Maps.newHashMap()
        ) { map: HashMap<String, Int> ->
            var i = 0
            while (i < 54) {
                map["container.$i"] = i
                ++i
            }
            i = 0
            while (i < 9) {
                map["hotbar.$i"] = i
                ++i
            }
            i = 0
            while (i < 27) {
                map["inventory.$i"] = 9 + i
                ++i
            }
            i = 0
            while (i < 27) {
                map["enderchest.$i"] = 200 + i
                ++i
            }
            i = 0
            while (i < 8) {
                map["villager.$i"] = 300 + i
                ++i
            }
            i = 0
            while (i < 15) {
                map["horse.$i"] = 500 + i
                ++i
            }
            map["weapon"] = EquipmentSlot.MAINHAND.getOffsetEntitySlotId(98)
            map["weapon.mainhand"] = EquipmentSlot.MAINHAND.getOffsetEntitySlotId(98)
            map["weapon.offhand"] = EquipmentSlot.OFFHAND.getOffsetEntitySlotId(98)
            map["armor.head"] = EquipmentSlot.HEAD.getOffsetEntitySlotId(100)
            map["armor.chest"] = EquipmentSlot.CHEST.getOffsetEntitySlotId(100)
            map["armor.legs"] = EquipmentSlot.LEGS.getOffsetEntitySlotId(100)
            map["armor.feet"] = EquipmentSlot.FEET.getOffsetEntitySlotId(100)
            map["horse.saddle"] = 400
            map["horse.armor"] = 401
            map["horse.chest"] = 499
        }

        fun isInSlot(slotName: String): GemstoneStatusEffectPredicate {
            return object : GemstoneStatusEffectPredicate {
                override fun calculate(world: World, entity: Entity, slot: Int, selected: Boolean): Boolean {
                    return slot == SLOT_NAMES[slotName]
                }
            }
        }

        val TRUE: GemstoneStatusEffectPredicate = object : GemstoneStatusEffectPredicate {
            override fun calculate(world: World, entity: Entity, slot: Int, selected: Boolean): Boolean = true
        }

        val IS_SELECTED: GemstoneStatusEffectPredicate = object : GemstoneStatusEffectPredicate {
            override fun calculate(world: World, entity: Entity, slot: Int, selected: Boolean): Boolean = selected
        }

        val IS_BEING_HELD: GemstoneStatusEffectPredicate = object : GemstoneStatusEffectPredicate {
            override fun calculate(world: World, entity: Entity, slot: Int, selected: Boolean): Boolean {
                return isInSlot("weapon.mainhand").calculate(world, entity, slot, selected) ||
                        isInSlot("weapon.offhand").calculate(world, entity, slot, selected)
            }
        }

        val IS_IN_ARMOR_SLOT: GemstoneStatusEffectPredicate = object : GemstoneStatusEffectPredicate {
            override fun calculate(world: World, entity: Entity, slot: Int, selected: Boolean): Boolean {
                return isInSlot("armor.head").calculate(world, entity, slot, selected) ||
                        isInSlot("armor.chest").calculate(world, entity, slot, selected) ||
                        isInSlot("armor.legs").calculate(world, entity, slot, selected) ||
                        isInSlot("armor.feet").calculate(world, entity, slot, selected)
            }
        }

        val IS_EQUIPPED: GemstoneStatusEffectPredicate = object : GemstoneStatusEffectPredicate {
            override fun calculate(world: World, entity: Entity, slot: Int, selected: Boolean): Boolean {
                return IS_BEING_HELD.calculate(world, entity, slot, selected) ||
                        IS_IN_ARMOR_SLOT.calculate(world, entity, slot, selected)
            }
        }
    }

    fun calculate(world: World, entity: Entity, slot: Int, selected: Boolean): Boolean
}