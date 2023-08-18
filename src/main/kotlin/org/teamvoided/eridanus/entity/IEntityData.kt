package org.teamvoided.eridanus.entity

import net.minecraft.nbt.NbtCompound

interface IEntityData {
    fun getPersistentData(): NbtCompound
}