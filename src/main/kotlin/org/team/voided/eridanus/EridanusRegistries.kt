package org.team.voided.eridanus

import org.team.voided.eridanus.item.gemstone.Gemstone
import org.team.voided.eridanus.item.gemstone.GemstoneSlot
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.util.registry.Registry

class EridanusRegistries {
    companion object {
        @JvmField val GEMSTONE: Registry<Gemstone> = FabricRegistryBuilder.createSimple(Gemstone::class.java, id("gemstone_registry")).buildAndRegister()
        @JvmField val GEMSTONE_SLOT: Registry<GemstoneSlot> = FabricRegistryBuilder.createSimple(GemstoneSlot::class.java, id("gemstone_slot_registry")).buildAndRegister()
    }
}