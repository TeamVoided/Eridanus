package com.team.voided.client

import com.team.voided.entity.EridanusEntities
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

private var enderDebug: KeyBinding? = null

@Suppress("unused")
fun onInitializeClient() {
    enderDebug = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "key.eridanus.ender_debug",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_I,
            "category.eridanus.key_binds"
        )
    )

    EridanusEntities.registerRenderers()
}