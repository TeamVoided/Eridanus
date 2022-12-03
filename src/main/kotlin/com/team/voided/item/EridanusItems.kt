package com.team.voided.item

import com.team.voided.VoidRarity
import com.team.voided.id
import com.team.voided.item.gemstone.Gemstone
import com.team.voided.item.gemstone.GemstoneItem
import com.team.voided.item.gemstone.GemstonePredicate
import com.team.voided.item.gemstone.GemstoneType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

class EridanusItems {
    companion object {
        val VOID_SHARD = VoidShardItem(Item.Settings(), VoidRarity.COMMON)
        val VOID_SHARD_POUCH = VoidShardPouch(Item.Settings())
        val OPAL: Gemstone = Gemstone.Builder(id("opal"), GemstoneType.REINFORCEMENT)
            .modifyAttribute(EntityAttributes.GENERIC_MAX_HEALTH, EntityAttributeModifier.Operation.ADDITION, 10.0, EquipmentSlot.HEAD, GemstonePredicate.TRUE)
            .modifyAttribute(EntityAttributes.GENERIC_MAX_HEALTH, EntityAttributeModifier.Operation.ADDITION, 10.0, EquipmentSlot.CHEST, GemstonePredicate.TRUE)
            .modifyAttribute(EntityAttributes.GENERIC_MAX_HEALTH, EntityAttributeModifier.Operation.ADDITION, 10.0, EquipmentSlot.LEGS, GemstonePredicate.TRUE)
            .modifyAttribute(EntityAttributes.GENERIC_MAX_HEALTH, EntityAttributeModifier.Operation.ADDITION, 10.0, EquipmentSlot.FEET, GemstonePredicate.TRUE)
            .withApplyPredicate(GemstonePredicate.IS_ARMOR)
            .buildAndRegister()
        val OPAL_ITEM: GemstoneItem = GemstoneItem(Item.Settings(), OPAL)

        fun register() {
            Registry.register(Registry.ITEM, id("void_shard"), VOID_SHARD)
            Registry.register(Registry.ITEM, id("void_shard_pouch"), VOID_SHARD_POUCH)
            Registry.register(Registry.ITEM, id("opal_item"), OPAL_ITEM)
        }
    }
}