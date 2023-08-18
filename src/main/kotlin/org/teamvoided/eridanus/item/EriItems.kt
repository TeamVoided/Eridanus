package org.teamvoided.eridanus.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.eridanus.Eridanus.id
import org.teamvoided.eridanus.item.EriTabs.ERIDANUS_ITEMS


@Suppress("unused")
object EriItems {

    // Items are made here
    val STAR = Item(FabricItemSettings())
    val STAR_FRAGMENT = Item(FabricItemSettings())



    fun init() {
        // Then you register them here
        register("star", STAR)
        register("star_fragment", STAR_FRAGMENT)

        ItemGroupEvents.modifyEntriesEvent(ERIDANUS_ITEMS)
            .register(ModifyEntries { entries ->
                entries.addAll(
                    listOf(
                        // Add them to the creative tab
                        STAR,
                        STAR_FRAGMENT


                    ).map { it.defaultStack }
                )
            })
    }

    private fun register(path: String, item: Item): Item = Registry.register(Registries.ITEM, id(path), item)

}