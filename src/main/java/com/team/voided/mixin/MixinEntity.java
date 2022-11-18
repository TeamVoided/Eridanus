package com.team.voided.mixin;

import com.team.voided.entity.IEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MixinEntity implements IEntityData {
    private NbtCompound persistentData;
    private static final String KEY = "persistent_data.5673332";

    @NotNull
    @Override
    public NbtCompound getPersistentData() {
        if (persistentData == null)
            persistentData = new NbtCompound();

        return persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void writePersistentData(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        if (persistentData != null) {
            nbt.put(KEY, persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void readPersistentData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains(KEY, 10)) {
            persistentData = nbt.getCompound(KEY);
        }
    }
}
