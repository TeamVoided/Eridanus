package com.team.voided

import net.minecraft.util.Formatting

enum class VoidRarity(formattingArg: Formatting) {
    COMMON(Formatting.WHITE),
    RARE(Formatting.YELLOW),
    CHAMPION(Formatting.GREEN),
    LEGENDARY(Formatting.BLUE),
    MYTHICAL(Formatting.LIGHT_PURPLE);

    private val formatting: Formatting = formattingArg

    fun getFormatting(): Formatting = formatting
}