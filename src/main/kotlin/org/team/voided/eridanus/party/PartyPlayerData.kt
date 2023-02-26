package org.team.voided.eridanus.party

import net.minecraft.nbt.NbtCompound
import java.util.UUID

data class PartyPlayerData(val playerId: UUID, var damageDealt: Double, var damageReceived: Double, var cratesCollected: Long) {
    companion object {
        fun create(playerId: UUID): PartyPlayerData {
            return PartyPlayerData(playerId, 0.0, 0.0, 0)
        }

        fun loadFromNbt(nbt: NbtCompound): PartyPlayerData {
            val pid = nbt.getUuid("pid")
            val ppd = create(pid)
            ppd.read(nbt)
            return ppd
        }
    }

    val contribution
        get() = ((damageDealt - damageReceived) / 10) + cratesCollected

    fun write(nbt: NbtCompound) {
        nbt.putUuid("pid", playerId)
        nbt.putDouble("dd", damageDealt)
        nbt.putDouble("dr", damageReceived)
        nbt.putLong("cc", cratesCollected)
    }

    fun read(nbt: NbtCompound) {
        damageDealt = nbt.getDouble("dd")
        damageReceived = nbt.getDouble("dr")
        cratesCollected = nbt.getLong("cc")
    }

    fun reset() {
        damageDealt = 0.0
        damageReceived = 0.0
        cratesCollected = 0
    }
}
