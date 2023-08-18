package org.teamvoided.eridanus

import net.minecraft.util.Formatting
@Suppress("unused")
enum class VoidRarity(formattingArg: Formatting) {
    COMMON(Formatting.WHITE),
    RARE(Formatting.GREEN),
    LEGENDARY(Formatting.RED),
    MYTHICAL(Formatting.DARK_PURPLE),
    VOID(Formatting.BLACK),
    LUNAR(Formatting.DARK_GRAY),
    MYSTICAL(Formatting.GOLD),
    BOSS(Formatting.DARK_RED),
    ELITE(Formatting.DARK_AQUA),
    ASTRAL(Formatting.LIGHT_PURPLE),
    LOST(Formatting.AQUA);

    private val formatting: Formatting = formattingArg

    fun getFormatting(): Formatting = formatting
}