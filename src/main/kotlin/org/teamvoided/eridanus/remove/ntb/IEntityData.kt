package org.teamvoided.eridanus.remove.ntb

import net.minecraft.nbt.NbtCompound

interface IEntityData {
    fun `eridanus$getPersistentData`(): NbtCompound
}