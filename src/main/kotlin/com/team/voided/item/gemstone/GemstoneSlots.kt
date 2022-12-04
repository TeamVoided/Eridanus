package com.team.voided.item.gemstone

import com.team.voided.id

class GemstoneSlots {
    companion object {
        val REFINEMENT = GemstoneSlot.Builder(id("refinement")).buildAndRegister()
        val REINFORCEMENT = GemstoneSlot.Builder(id("reinforcement")).buildAndRegister()
    }
}