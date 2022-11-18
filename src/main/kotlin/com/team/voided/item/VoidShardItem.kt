package com.team.voided.item

import com.team.voided.VoidRarity
import com.team.voided.entity.EntityNBTHelper
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class VoidShardItem(settings: Settings?, rarityArg: VoidRarity) : Item(settings) {
    private val rarity: VoidRarity = rarityArg

    companion object {
        fun addVoidShardsToPlayer(shards: Long, player: PlayerEntity) {
            if (!EntityNBTHelper.contains("void_shards", player)) {
                EntityNBTHelper.putLong("void_shards", shards, player)
                return
            }

            EntityNBTHelper.putLong("void_shards", getVoidShardsFromPlayer(player) + shards, player)
        }

        fun removeVoidShardsFromPlayer(shards: Long, player: PlayerEntity) {
            if (!EntityNBTHelper.contains("void_shards", player)) {
                EntityNBTHelper.putLong("void_shards", 0, player)
                return
            }

            EntityNBTHelper.putLong("void_shards", getVoidShardsFromPlayer(player) - shards, player)
        }

        fun getVoidShardsFromPlayer(player: PlayerEntity): Long {
            if (!EntityNBTHelper.contains("void_shards", player)) {
                EntityNBTHelper.putLong("void_shards", 0, player)
                return 0
            }

            return EntityNBTHelper.getLong("void_shards", player)
        }
    }

    fun getRarity(): VoidRarity = rarity

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        user!!

        when (rarity) {
            VoidRarity.COMMON -> { addVoidShardsToPlayer(10, user) }
            VoidRarity.RARE -> { addVoidShardsToPlayer(30, user) }
            VoidRarity.CHAMPION -> { addVoidShardsToPlayer(50, user) }
            VoidRarity.LEGENDARY -> { addVoidShardsToPlayer(100, user) }
            VoidRarity.MYTHICAL -> { addVoidShardsToPlayer(250, user) }
        }

        val stackInHand = user.getStackInHand(hand)
        stackInHand.decrement(1)

        return TypedActionResult.consume(stackInHand)
    }
}