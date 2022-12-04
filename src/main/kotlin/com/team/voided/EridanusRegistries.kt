package com.team.voided

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.team.voided.item.gemstone.Gemstone
import com.team.voided.item.gemstone.GemstoneItem
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.util.registry.Registry

class EridanusRegistries {
    companion object {
        @JvmField val GEMSTONE: Registry<Gemstone> = FabricRegistryBuilder.createSimple(Gemstone::class.java, id("gemstone_registry")).buildAndRegister()
    }
}