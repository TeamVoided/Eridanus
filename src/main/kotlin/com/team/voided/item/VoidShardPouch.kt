package com.team.voided.item

import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.ClickType
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import kotlin.math.floor

class VoidShardPouch(settings: Settings?) : Item(settings) {
    companion object {
        private const val KEY = "vsp.shards.c"

        fun getStoredShards(itemStack: ItemStack): Long
             = ItemNBTHelper.getLong(KEY, itemStack)

        fun setStoredShards(shards: Long, itemStack: ItemStack)
            = ItemNBTHelper.putLong(KEY, shards, itemStack)

        fun addShards(shards: Long, itemStack: ItemStack)
            = setStoredShards(getStoredShards(itemStack) + shards, itemStack)

        fun removeShards(shards: Long, itemStack: ItemStack)
            = addShards(-shards, itemStack)

        fun removeShardsToInventory(shards: Long, inventory: PlayerInventory, itemStack: ItemStack) {
            val storedShards = getStoredShards(itemStack)

            if (storedShards < 0) return

            if (storedShards < 64) {
                removeShards(storedShards, itemStack)
                inventory.offerOrDrop(ItemStack(EridanusItems.VOID_SHARD, storedShards.toInt()))

                return
            }

            removeShards(shards, itemStack)

            val rem = (shards % 64).toInt()
            val amt = floor(shards / 64f).toInt()

            for (i in 0 until amt) {
                inventory.offerOrDrop(ItemStack(EridanusItems.VOID_SHARD, 64))
            }

            inventory.offerOrDrop(ItemStack(EridanusItems.VOID_SHARD, rem))
        }

        fun addShardsFromInventory(shards: Long, inventory: PlayerInventory, itemStack: ItemStack) {
            var l = shards

            inventory.main.forEach { stack ->
                if (l == 0L) return

                if (stack.item == EridanusItems.VOID_SHARD) {
                    if ((l - stack.count) < 0 || (l - stack.count) < 64) {
                        if (stack.count > l) {
                            addShards(stack.count.toLong(), itemStack)
                            stack.decrement(l.toInt())
                            l = 0
                            return
                        }

                        l -= stack.count
                        addShards(stack.count.toLong(), itemStack)
                        stack.decrement(stack.count)
                        return
                    }

                    l -= 64
                    addShards(stack.count.toLong(), itemStack)
                    stack.decrement(64)
                }
            }
        }
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stackInHand = user.getStackInHand(hand)

        if (user.isInSneakingPose) {
            addShardsFromInventory(Long.MAX_VALUE, user.inventory, stackInHand)
            playInsertSound(user)
        }
        else {
            removeShardsToInventory(64L, user.inventory, stackInHand)
            playRemoveOneSound(user)
        }

        return TypedActionResult.success(stackInHand)
    }

    override fun onStackClicked(stack: ItemStack, slot: Slot, clickType: ClickType, player: PlayerEntity): Boolean {
        if (clickType == ClickType.RIGHT) {
            if (slot.stack.isEmpty) {
                val size = getStoredShards(stack)

                if (size < 64) {
                    slot.stack = ItemStack(EridanusItems.VOID_SHARD, size.toInt())
                    setStoredShards(0, stack)
                    playRemoveOneSound(player)
                } else {
                    slot.stack = ItemStack(EridanusItems.VOID_SHARD, 64)
                    removeShards(64, stack)
                    playRemoveOneSound(player)
                }
            } else {
                val size = slot.stack.count
                slot.stack.decrement(size)
                addShards(size.toLong(), stack)
                playInsertSound(player)
            }

            return true
        }

        return false
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        tooltip.add(Text.translatable("eridanus.vsp.c", getStoredShards(stack)).formatted(Formatting.DARK_GRAY))
    }

    private fun playRemoveOneSound(entity: Entity)
        = entity.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, 0.8f, 0.8f + entity.getWorld().getRandom().nextFloat() * 0.4f)

    private fun playInsertSound(entity: Entity)
        = entity.playSound(SoundEvents.ITEM_BUNDLE_INSERT, 0.8f, 0.8f + entity.getWorld().getRandom().nextFloat() * 0.4f)
}