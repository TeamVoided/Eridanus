package com.team.voided

import com.team.voided.item.gemstone.Gemstone
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.util.registry.Registry

class EridanusRegistries {
    companion object {
        @JvmField val GEMSTONE: Registry<Gemstone> = FabricRegistryBuilder.createSimple(Gemstone::class.java, id("gemstone_registry")).buildAndRegister()
    }
}