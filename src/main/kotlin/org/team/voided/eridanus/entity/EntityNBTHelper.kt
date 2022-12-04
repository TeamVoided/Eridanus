package org.team.voided.eridanus.entity

import net.minecraft.entity.Entity
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.nbt.NbtList
import java.util.*

class EntityNBTHelper private constructor() {
    companion object {
        fun put(key: String, element: NbtElement, entity: Entity): NbtElement? = (entity as IEntityData).getPersistentData().put(key, element)
        fun putByte(key: String, byte: Byte, entity: Entity) = (entity as IEntityData).getPersistentData().putByte(key, byte)
        fun putShort(key: String, short: Short, entity: Entity) = (entity as IEntityData).getPersistentData().putShort(key, short)
        fun putInt(key: String, int: Int, entity: Entity) = (entity as IEntityData).getPersistentData().putInt(key, int)
        fun putLong(key: String, long: Long, entity: Entity) = (entity as IEntityData).getPersistentData().putLong(key, long)
        fun putUuid(key: String, uuid: UUID, entity: Entity) = (entity as IEntityData).getPersistentData().putUuid(key, uuid)
        fun getUuid(key: String, entity: Entity): UUID = (entity as IEntityData).getPersistentData().getUuid(key)
        fun containsUuid(key: String, entity: Entity): Boolean = (entity as IEntityData).getPersistentData().containsUuid(key)
        fun putFloat(key: String, float: Float, entity: Entity) = (entity as IEntityData).getPersistentData().putFloat(key, float)
        fun putDouble(key: String, double: Double, entity: Entity) = (entity as IEntityData).getPersistentData().putDouble(key, double)
        fun putString(key: String, string: String, entity: Entity) = (entity as IEntityData).getPersistentData().putString(key, string)
        fun putByteArray(key: String, byteArray: ByteArray, entity: Entity) = (entity as IEntityData).getPersistentData().putByteArray(key, byteArray)
        fun putByteArray(key: String, byteArray: List<Byte>, entity: Entity) = (entity as IEntityData).getPersistentData().putByteArray(key, byteArray)
        fun putIntArray(key: String, intArray: IntArray, entity: Entity) = (entity as IEntityData).getPersistentData().putIntArray(key, intArray)
        fun putIntArray(key: String, intArray: List<Int>, entity: Entity) = (entity as IEntityData).getPersistentData().putIntArray(key, intArray)
        fun putLongArray(key: String, longArray: LongArray, entity: Entity) = (entity as IEntityData).getPersistentData().putLongArray(key, longArray)
        fun putLongArray(key: String, longArray: List<Long>, entity: Entity) = (entity as IEntityData).getPersistentData().putLongArray(key, longArray)
        fun putBoolean(key: String, boolean: Boolean, entity: Entity) = (entity as IEntityData).getPersistentData().putBoolean(key, boolean)
        fun get(key: String, entity: Entity): NbtElement? = (entity as IEntityData).getPersistentData()[key]
        fun getType(key: String, entity: Entity): Byte = (entity as IEntityData).getPersistentData().getType(key)
        fun contains(key: String, entity: Entity): Boolean = (entity as IEntityData).getPersistentData().contains(key)
        fun contains(key: String, type: Int, entity: Entity): Boolean = (entity as IEntityData).getPersistentData().contains(key, type)
        fun getByte(key: String, entity: Entity): Byte = (entity as IEntityData).getPersistentData().getByte(key)
        fun getShort(key: String, entity: Entity): Short = (entity as IEntityData).getPersistentData().getShort(key)
        fun getInt(key: String, entity: Entity): Int = (entity as IEntityData).getPersistentData().getInt(key)
        fun getLong(key: String, entity: Entity): Long = (entity as IEntityData).getPersistentData().getLong(key)
        fun getFloat(key: String, entity: Entity): Float = (entity as IEntityData).getPersistentData().getFloat(key)
        fun getDouble(key: String, entity: Entity): Double = (entity as IEntityData).getPersistentData().getDouble(key)
        fun getString(key: String, entity: Entity): String = (entity as IEntityData).getPersistentData().getString(key)
        fun getByteArray(key: String, entity: Entity): ByteArray = (entity as IEntityData).getPersistentData().getByteArray(key)
        fun getIntArray(key: String, entity: Entity): IntArray = (entity as IEntityData).getPersistentData().getIntArray(key)
        fun getLongArray(key: String, entity: Entity): LongArray = (entity as IEntityData).getPersistentData().getLongArray(key)
        fun getCompound(key: String, entity: Entity): NbtCompound = (entity as IEntityData).getPersistentData().getCompound(key)
        fun getList(key: String, type: Int, entity: Entity): NbtList = (entity as IEntityData).getPersistentData().getList(key, type)
        fun getBoolean(key: String, entity: Entity): Boolean = (entity as IEntityData).getPersistentData().getBoolean(key)
        fun remove(key: String, entity: Entity) = (entity as IEntityData).getPersistentData().remove(key)
        fun isEmpty(entity: Entity): Boolean = (entity as IEntityData).getPersistentData().isEmpty
        fun subNbt(key: String, entity: Entity): NbtCompound {
            val persistentData = (entity as IEntityData).getPersistentData()
            if (persistentData.contains(key, 10))
                return persistentData.getCompound(key)

            val compound = NbtCompound()
            persistentData.put(key, compound)

            return compound
        }
    }
}