package org.teamvoided.eridanus

import org.teamvoided.eridanus.item.gemstone.Gemstone
import org.teamvoided.eridanus.item.gemstone.GemstoneSlot
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.registry.Registry
import org.teamvoided.eridanus.Eridanus.id

class EridanusRegistries {
    companion object {
        @JvmField val GEMSTONE: Registry<Gemstone> = FabricRegistryBuilder.createSimple(Gemstone::class.java, id("gemstone_registry")).buildAndRegister()
        @JvmField val GEMSTONE_SLOT: Registry<GemstoneSlot> = FabricRegistryBuilder.createSimple(GemstoneSlot::class.java, id("gemstone_slot_registry")).buildAndRegister()
    }
}