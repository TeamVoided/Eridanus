package com.team.voided.entity

import net.minecraft.nbt.NbtCompound

interface IEntityData {
    fun getPersistentData(): NbtCompound
}