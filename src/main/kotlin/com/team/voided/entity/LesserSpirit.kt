package com.team.voided.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Arm
import net.minecraft.world.World
import java.util.*

class LesserSpirit(entityType: EntityType<out LivingEntity>, world: World) : LivingEntity(entityType, world) {
    override fun getArmorItems(): MutableIterable<ItemStack> = LinkedList()

    override fun equipStack(slot: EquipmentSlot, stack: ItemStack) { }

    override fun getEquippedStack(slot: EquipmentSlot): ItemStack = ItemStack.EMPTY

    override fun getMainArm(): Arm = Arm.RIGHT
}