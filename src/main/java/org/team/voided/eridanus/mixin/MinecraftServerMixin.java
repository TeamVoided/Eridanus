package org.team.voided.eridanus.mixin;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.team.voided.eridanus.party.PartyManager;

import java.io.IOException;

import static org.team.voided.eridanus.EridanusKt.LOGGER;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Inject(method = "save", at = @At("HEAD"))
    void saveCustomData(boolean suppressLogs, boolean flush, boolean force, CallbackInfoReturnable<Boolean> cir) throws IOException {
        LOGGER.info("Starting PartyManager save");
        if (!PartyManager.getSaveFile().exists()) PartyManager.getSaveFile().createNewFile();
        NbtCompound nbt = new NbtCompound();
        PartyManager.INSTANCE.write(nbt);
        NbtIo.writeCompressed(nbt, PartyManager.getSaveFile());
        LOGGER.info("Finished saving PartyManager");
    }
}
