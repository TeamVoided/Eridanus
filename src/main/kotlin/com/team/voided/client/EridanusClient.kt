package com.team.voided.client

import com.team.voided.item.VoidShardItem
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW

private var openStatsGui: KeyBinding? = null
private var enderDebug: KeyBinding? = null

@Suppress("unused")
fun onInitializeClient() {
    openStatsGui = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "key.eridanus.stats_gui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_Y,
            "category.eridanus.key_binds"
        )
    )
    enderDebug = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "key.eridanus.ender_debug",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_I,
            "category.eridanus.key_binds"
        )
    )

    ClientTickEvents.END_CLIENT_TICK.register { client ->
        while (openStatsGui!!.wasPressed()) {
            val player = client.player!!.inventory.player
            player.sendMessage(Text.of("You have ${VoidShardItem.getVoidShardsFromPlayer(player)} void shards"))
        }

    }
}