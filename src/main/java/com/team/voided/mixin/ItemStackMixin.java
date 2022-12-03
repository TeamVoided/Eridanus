package com.team.voided.mixin;

import com.team.voided.item.gemstone.Gemstone;
import com.team.voided.item.gemstone.GemstoneHelper;
import com.team.voided.item.gemstone.GemstoneType;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract Item getItem();

    @Shadow public abstract ItemStack copy();

    @Shadow public abstract void setCount(int count);

    @Shadow public abstract void setDamage(int damage);

    @Shadow public abstract void setNbt(@Nullable NbtCompound nbt);

    @Shadow public abstract void setHolder(@Nullable Entity holder);

    @Shadow public abstract ItemStack setCustomName(@Nullable Text name);

    @Shadow public abstract void setRepairCost(int repairCost);

    @Shadow public abstract void setBobbingAnimationTime(int bobbingAnimationTime);

    @Inject(method = "getMiningSpeedMultiplier", at = @At("RETURN"), cancellable = true)
    private void changeMiningSpeedMultiplier(BlockState state, CallbackInfoReturnable<Float> cir) {
        ItemStack copy = copy();
        float def = getItem().getMiningSpeedMultiplier(copy, state);

        copyParams(copy);

        for (GemstoneType type : GemstoneType.values()) {
            Gemstone gemstone = GemstoneHelper.Companion.getGemstone(type, copy);
            if (gemstone != null) {
                def = gemstone.calculateMiningSpeedModifier(def);
            }
        }

        cir.setReturnValue(def);
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void applyGemstoneEffects(World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        ItemStack copy = copy();

        for (GemstoneType type : GemstoneType.values()) {
            Gemstone gemstone = GemstoneHelper.Companion.getGemstone(type, copy);
            if (gemstone != null) {
                gemstone.applyStatusEffects(entity, slot);
            }
        }
    }

    @Inject(method = "getTooltip", at = @At("RETURN"))
    private void addGemstoneTooltip(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir) {
        List<Text> current = cir.getReturnValue();

        for (GemstoneType type : GemstoneType.values()) {
            Gemstone gemstone = GemstoneHelper.Companion.getGemstone(type, copy());
            if (gemstone != null) {
                current.add(Text.translatable("gemstone.%1s.%2s".formatted(gemstone.getId().getNamespace(), gemstone.getId().getPath())));
            }
        }
    }

    private void copyParams(ItemStack copy) {
        setCount(copy.getCount());
        setDamage(copy.getDamage());
        setNbt(copy.getNbt());
        setHolder(copy.getHolder());
        setCustomName(copy.getName());
        setRepairCost(copy.getRepairCost());
        setBobbingAnimationTime(copy.getBobbingAnimationTime());
    }
}
