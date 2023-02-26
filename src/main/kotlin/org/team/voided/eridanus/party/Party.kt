package org.team.voided.eridanus.party

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.loot.LootTable
import net.minecraft.nbt.NbtCompound
import org.team.voided.eridanus.party.PartyManager.isInSpecificParty
import org.team.voided.eridanus.party.PartyManager.isInParty
import java.util.LinkedList
import java.util.UUID

class Party(val id: UUID) {
    companion object {
        fun loadFromNbt(nbt: NbtCompound): Party {
            val id = nbt.getUuid("id")
            val party = Party(id)
            party.read(nbt)
            return party
        }
    }

    private val members: MutableList<Pair<UUID, PartyPlayerData>> = LinkedList()

    fun addMember(player: PlayerEntity): Boolean {
        if (player.isInParty()) return false
        members.add(Pair(player.uuid, PartyPlayerData.create(player.uuid)))

        return true
    }

    fun removeMember(member: PlayerEntity): Boolean {
        if (!member.isInSpecificParty(this)) return false
        return members.removeIf { it.first == member.uuid }
    }

    fun percentTotalContribution(member: PlayerEntity): Double {
        if (!member.isInSpecificParty(this)) return 0.0
        return members.first { it.first == member.uuid }.second.contribution / members.sumOf { it.second.contribution }
    }

    fun divideLoot(loot: LootTable) {}

    fun write(nbt: NbtCompound) {
        nbt.putInt("size", members.size)
        members.forEachIndexed { index, (_, data) ->
            val ppd = NbtCompound()
            data.write(ppd)
            nbt.put("${index}_ppd", ppd)
        }
        nbt.putUuid("id", id)
    }

    fun read(nbt: NbtCompound) {
        val data: MutableList<PartyPlayerData> = LinkedList()

        val size = nbt.getInt("size")
        for (i in 0 until size) {
            val ppd = nbt.get("${i}_ppd") as NbtCompound
            data.add(PartyPlayerData.loadFromNbt(ppd))
        }

        members.removeAll(members)
        members.addAll(data.map { Pair(it.playerId, it) })
    }

    fun getMembers(): List<UUID> {
        return members.map { it.first }
    }
}