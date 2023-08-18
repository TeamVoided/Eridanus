package org.teamvoided.eridanus.item.gemstone

import org.teamvoided.eridanus.Eridanus.id
@Suppress("unused")
class GemstoneSlots {
    companion object {
        val REFINEMENT = GemstoneSlot.Builder(id("refinement")).buildAndRegister()
        val REINFORCEMENT = GemstoneSlot.Builder(id("reinforcement")).buildAndRegister()
    }
}