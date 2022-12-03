package com.team.voided.item

import com.team.voided.VoidRarity
import com.team.voided.id
import com.team.voided.item.gemstone.Gemstone
import com.team.voided.item.gemstone.GemstoneItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

class EridanusItems {
    companion object {
        val VOID_SHARD = VoidShardItem(Item.Settings(), VoidRarity.COMMON)
        val VOID_SHARD_POUCH = VoidShardPouch(Item.Settings())
        val OPAL: Gemstone = Gemstone.Builder(id("opal"))
            .modifyAttribute(EntityAttributes.GENERIC_MAX_HEALTH, EntityAttributeModifier.Operation.ADDITION, 10.0, EquipmentSlot.HEAD)
            .addStatusEffect(StatusEffects.ABSORPTION, 3)
            .setMiningSpeedModifier(EntityAttributeModifier.Operation.ADDITION, 2f).buildAndRegister()
        val OPAL_ITEM: GemstoneItem = GemstoneItem(Item.Settings(), OPAL)

        fun register() {
            Registry.register(Registry.ITEM, id("void_shard"), VOID_SHARD)
            Registry.register(Registry.ITEM, id("void_shard_pouch"), VOID_SHARD_POUCH)
            Registry.register(Registry.ITEM, id("opal_item"), OPAL_ITEM)
        }
    }
}