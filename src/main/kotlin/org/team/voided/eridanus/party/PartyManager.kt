package org.team.voided.eridanus.party

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtIo
import java.io.File
import java.util.LinkedList
import java.util.UUID

object PartyManager {
    init {
        ServerLifecycleEvents.SERVER_STARTING.register {
            if (saveFile.exists()) {
                read(NbtIo.readCompressed(saveFile))
            }
        }
    }

    private val parties: MutableList<Party> = LinkedList()
    @JvmStatic val saveFile = File("eridanus/partymanager.dat")

    fun isPlayerInSpecificParty(player: PlayerEntity, party: Party): Boolean {
        return party.getMembers().contains(player.uuid)
    }

    fun isPlayerInParty(player: PlayerEntity): Boolean {
        val allPartiedPlayers: MutableList<UUID> = LinkedList()
        parties.forEach {
            allPartiedPlayers.addAll(it.getMembers())
        }

        return allPartiedPlayers.contains(player.uuid)
    }

    fun write(nbt: NbtCompound) {
        nbt.putInt("size", parties.size)
        parties.forEachIndexed { index, party ->
            val pNbt = NbtCompound()
            party.write(pNbt)
            nbt.put("${index}_party", pNbt)
        }
    }

    fun read(nbt: NbtCompound) {
        val tempParties: MutableList<Party> = LinkedList()

        val size = nbt.getInt("size")
        for (i in 0 until size) {
            tempParties.add(Party.loadFromNbt(nbt.get("${i}_party") as NbtCompound))
        }

        parties.removeAll(parties)
        parties.addAll(tempParties)
    }

    fun init() {}

    fun PlayerEntity.isInSpecificParty(party: Party): Boolean = isPlayerInSpecificParty(this, party)
    fun PlayerEntity.isInParty(): Boolean = isPlayerInParty(this)
}