package org.team.voided.eridanus

import net.minecraft.util.Formatting

enum class VoidRarity(formattingArg: Formatting) {
    COMMON(Formatting.WHITE),
    RARE(Formatting.GREEN),
    LEGENDARY(Formatting.GOLD),
    MYTHICAL(Formatting.DARK_PURPLE),
    VOID(Formatting.BLACK),
    LUNAR(Formatting.DARK_GRAY),
    //There is no orange colour and I am not sure what to do about that so im going to set it as white
    //until I know what to do.
    MYSTICAL(Formatting.WHITE),
    BOSS(Formatting.DARK_RED),
    ELITE(Formatting.DARK_AQUA),
    ASTRAL(Formatting.LIGHT_PURPLE),
    LOST(Formatting.AQUA);

    private val formatting: Formatting = formattingArg

    fun getFormatting(): Formatting = formatting
}