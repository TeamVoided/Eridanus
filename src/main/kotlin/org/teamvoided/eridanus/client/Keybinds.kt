package org.teamvoided.eridanus.client

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW

object Keybinds {
    val debugKey: KeyBinding = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "key.eridanus.debug",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_I,
            "category.eridanus.key_binds"
        )
    )

    fun init() {
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            val player = client.player
            if (debugKey.isPressed) {
                player?.sendMessage(Text.literal("Hello"))
            }

        }
    }
}