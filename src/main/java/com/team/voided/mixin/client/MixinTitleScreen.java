package com.team.voided.mixin.client;

import com.team.voided.EridanusKt;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen extends Screen {
    protected MixinTitleScreen(Text title) {
        super(title);
    }

    @Inject(method = "initWidgetsNormal", at = @At("RETURN"))
    private void addConfigsButton(int y, int spacingY, CallbackInfo ci) {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100 + 205, y, 50, 20, Text.translatable("menu.eridanus.configs"), (button) -> {
            this.client.setScreen(EridanusKt.clientConfig.createGui(this));
        }));
    }
}
