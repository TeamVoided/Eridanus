package org.team.voided.eridanus.item.gemstone

import org.team.voided.eridanus.id

class GemstoneSlots {
    companion object {
        val REFINEMENT = GemstoneSlot.Builder(id("refinement")).buildAndRegister()
        val REINFORCEMENT = GemstoneSlot.Builder(id("reinforcement")).buildAndRegister()
    }
}