package org.team.voided.eridanus.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import org.team.voided.eridanus.VoidRarity
import org.team.voided.eridanus.id
import org.team.voided.eridanus.item.gemstone.Gemstone
import org.team.voided.eridanus.item.gemstone.GemstoneItem
import org.team.voided.eridanus.item.gemstone.GemstonePredicate
import org.team.voided.eridanus.item.gemstone.GemstoneSlots
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

class EridanusItems {
    companion object {
        val VOID_SHARD = VoidShardItem(Item.Settings(), VoidRarity.COMMON)
        val VOID_SHARD_POUCH = VoidShardPouch(Item.Settings())
        val OPAL: Gemstone = Gemstone.Builder(id("opal"), GemstoneSlots.REINFORCEMENT)
            .modifyAttribute(EntityAttributes.GENERIC_MAX_HEALTH, EntityAttributeModifier.Operation.ADDITION, 10.0, EquipmentSlot.HEAD, GemstonePredicate.TRUE)
            .modifyAttribute(EntityAttributes.GENERIC_MAX_HEALTH, EntityAttributeModifier.Operation.ADDITION, 10.0, EquipmentSlot.CHEST, GemstonePredicate.TRUE)
            .modifyAttribute(EntityAttributes.GENERIC_MAX_HEALTH, EntityAttributeModifier.Operation.ADDITION, 10.0, EquipmentSlot.LEGS, GemstonePredicate.TRUE)
            .modifyAttribute(EntityAttributes.GENERIC_MAX_HEALTH, EntityAttributeModifier.Operation.ADDITION, 10.0, EquipmentSlot.FEET, GemstonePredicate.TRUE)
            .withApplyPredicate(GemstonePredicate.IS_ARMOR)
            .withItem(Item.Settings())
            .buildAndRegister()
        val OPAL_ITEM: GemstoneItem = OPAL.getItem()!!
        val STAR = Item(FabricItemSettings())
        val STAR_FRAGMENT = Item(FabricItemSettings())

        fun register() {
            Registry.register(Registries.ITEM, id("void_shard"), VOID_SHARD)
            Registry.register(Registries.ITEM, id("void_shard_pouch"), VOID_SHARD_POUCH)
            regItem("star", STAR)
            regItem("star_fragment", STAR_FRAGMENT)
        }
        fun regItem(path:String, item:Item) {
            Registry.register(Registries.ITEM, id(path), item)
        }
    }
}