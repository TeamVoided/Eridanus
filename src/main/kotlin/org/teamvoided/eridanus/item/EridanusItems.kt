package org.teamvoided.eridanus.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.eridanus.VoidRarity
import org.teamvoided.eridanus.Eridanus.id
import org.teamvoided.eridanus.item.ItemGroups.ERIDANUS_ITEMS
import org.teamvoided.eridanus.item.gemstone.Gemstone
import org.teamvoided.eridanus.item.gemstone.GemstoneItem
import org.teamvoided.eridanus.item.gemstone.GemstonePredicate
import org.teamvoided.eridanus.item.gemstone.GemstoneSlots

@Suppress("unused")
object EridanusItems {
    val VOID_SHARD: Item = VoidShardItem(Item.Settings(), VoidRarity.COMMON)
    val VOID_SHARD_POUCH: Item = VoidShardPouch(Item.Settings())
    val OPAL: Gemstone = Gemstone.Builder(id("opal"), GemstoneSlots.REINFORCEMENT)
        .modifyAttribute(
            EntityAttributes.GENERIC_MAX_HEALTH,
            EntityAttributeModifier.Operation.ADDITION,
            10.0,
            EquipmentSlot.HEAD,
            GemstonePredicate.TRUE
        )
        .modifyAttribute(
            EntityAttributes.GENERIC_MAX_HEALTH,
            EntityAttributeModifier.Operation.ADDITION,
            10.0,
            EquipmentSlot.CHEST,
            GemstonePredicate.TRUE
        )
        .modifyAttribute(
            EntityAttributes.GENERIC_MAX_HEALTH,
            EntityAttributeModifier.Operation.ADDITION,
            10.0,
            EquipmentSlot.LEGS,
            GemstonePredicate.TRUE
        )
        .modifyAttribute(
            EntityAttributes.GENERIC_MAX_HEALTH,
            EntityAttributeModifier.Operation.ADDITION,
            10.0,
            EquipmentSlot.FEET,
            GemstonePredicate.TRUE
        )
        .withApplyPredicate(GemstonePredicate.IS_ARMOR)
        .withItem(Item.Settings())
        .buildAndRegister()
    val OPAL_ITEM: GemstoneItem = OPAL.getItem()!!
    val STAR = Item(FabricItemSettings())
    val STAR_FRAGMENT = Item(FabricItemSettings())


    fun init() {
        Registry.register(Registries.ITEM, id("void_shard"), VOID_SHARD)
        Registry.register(Registries.ITEM, id("void_shard_pouch"), VOID_SHARD_POUCH)
        regItem("star", STAR)
        regItem("star_fragment", STAR_FRAGMENT)



        ItemGroupEvents.modifyEntriesEvent(ERIDANUS_ITEMS)
            .register(ModifyEntries {
                it.addAll(
                    listOf(
                        VOID_SHARD.defaultStack,
                        VOID_SHARD_POUCH.defaultStack,
                        STAR.defaultStack,
                        STAR_FRAGMENT.defaultStack
                    )
                )
            })
    }

    fun regItem(path: String, item: Item) = Registry.register(Registries.ITEM, id(path), item)

}