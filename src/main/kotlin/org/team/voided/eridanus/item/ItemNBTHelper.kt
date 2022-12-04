package org.team.voided.eridanus.item

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.nbt.NbtList
import java.util.*

class ItemNBTHelper private constructor() {
    companion object {
        fun put(key: String, element: NbtElement, stack: ItemStack): NbtElement? = stack.orCreateNbt.put(key, element)
        fun putByte(key: String, byte: Byte, stack: ItemStack) = stack.orCreateNbt.putByte(key, byte)
        fun putShort(key: String, short: Short, stack: ItemStack) = stack.orCreateNbt.putShort(key, short)
        fun putInt(key: String, int: Int, stack: ItemStack) = stack.orCreateNbt.putInt(key, int)
        fun putLong(key: String, long: Long, stack: ItemStack) = stack.orCreateNbt.putLong(key, long)
        fun putUuid(key: String, uuid: UUID, stack: ItemStack) = stack.orCreateNbt.putUuid(key, uuid)
        fun getUuid(key: String, stack: ItemStack): UUID = stack.orCreateNbt.getUuid(key)
        fun containsUuid(key: String, stack: ItemStack): Boolean = stack.orCreateNbt.containsUuid(key)
        fun putFloat(key: String, float: Float, stack: ItemStack) = stack.orCreateNbt.putFloat(key, float)
        fun putDouble(key: String, double: Double, stack: ItemStack) = stack.orCreateNbt.putDouble(key, double)
        fun putString(key: String, string: String, stack: ItemStack) = stack.orCreateNbt.putString(key, string)
        fun putByteArray(key: String, byteArray: ByteArray, stack: ItemStack) = stack.orCreateNbt.putByteArray(key, byteArray)
        fun putByteArray(key: String, byteArray: List<Byte>, stack: ItemStack) = stack.orCreateNbt.putByteArray(key, byteArray)
        fun putIntArray(key: String, intArray: IntArray, stack: ItemStack) = stack.orCreateNbt.putIntArray(key, intArray)
        fun putIntArray(key: String, intArray: List<Int>, stack: ItemStack) = stack.orCreateNbt.putIntArray(key, intArray)
        fun putLongArray(key: String, longArray: LongArray, stack: ItemStack) = stack.orCreateNbt.putLongArray(key, longArray)
        fun putLongArray(key: String, longArray: List<Long>, stack: ItemStack) = stack.orCreateNbt.putLongArray(key, longArray)
        fun putBoolean(key: String, boolean: Boolean, stack: ItemStack) = stack.orCreateNbt.putBoolean(key, boolean)
        fun get(key: String, stack: ItemStack): NbtElement? = stack.orCreateNbt[key]
        fun getType(key: String, stack: ItemStack): Byte = stack.orCreateNbt.getType(key)
        fun contains(key: String, stack: ItemStack): Boolean = stack.orCreateNbt.contains(key)
        fun contains(key: String, type: Int, stack: ItemStack): Boolean = stack.orCreateNbt.contains(key, type)
        fun getByte(key: String, stack: ItemStack): Byte = stack.orCreateNbt.getByte(key)
        fun getShort(key: String, stack: ItemStack): Short = stack.orCreateNbt.getShort(key)
        fun getInt(key: String, stack: ItemStack): Int = stack.orCreateNbt.getInt(key)
        fun getLong(key: String, stack: ItemStack): Long = stack.orCreateNbt.getLong(key)
        fun getFloat(key: String, stack: ItemStack): Float = stack.orCreateNbt.getFloat(key)
        fun getDouble(key: String, stack: ItemStack): Double = stack.orCreateNbt.getDouble(key)
        fun getString(key: String, stack: ItemStack): String = stack.orCreateNbt.getString(key)
        fun getByteArray(key: String, stack: ItemStack): ByteArray = stack.orCreateNbt.getByteArray(key)
        fun getIntArray(key: String, stack: ItemStack): IntArray = stack.orCreateNbt.getIntArray(key)
        fun getLongArray(key: String, stack: ItemStack): LongArray = stack.orCreateNbt.getLongArray(key)
        fun getCompound(key: String, stack: ItemStack): NbtCompound = stack.orCreateNbt.getCompound(key)
        fun getList(key: String, type: Int, stack: ItemStack): NbtList = stack.orCreateNbt.getList(key, type)
        fun getBoolean(key: String, stack: ItemStack): Boolean = stack.orCreateNbt.getBoolean(key)
        fun remove(key: String, stack: ItemStack) = stack.orCreateNbt.remove(key)
        fun isEmpty(stack: ItemStack): Boolean = stack.orCreateNbt.isEmpty
        fun subNbt(key: String, stack: ItemStack): NbtCompound = stack.getOrCreateSubNbt(key)
    }
}