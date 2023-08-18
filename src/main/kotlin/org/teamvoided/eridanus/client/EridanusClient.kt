package org.teamvoided.eridanus.client

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

private var debugKey: KeyBinding? = null

@Suppress("unused")
fun onInitializeClient() {
    debugKey = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "key.eridanus.debug",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_I,
            "category.eridanus.key_binds"
        )
    )
}