package com.team.voided.entity

import com.team.voided.entity.model.LesserSpiritModel
import com.team.voided.entity.renderer.LesserSpiritRenderer
import com.team.voided.id
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.util.registry.Registry

class EridanusEntities {
    companion object {
        val LESSER_SPIRIT: EntityType<LesserSpirit> = Registry.register(
            Registry.ENTITY_TYPE,
            id("lesser_spirit"),
            FabricEntityTypeBuilder.create<LesserSpirit>().build()
        )
        val LESSER_SPIRIT_LAYER = EntityModelLayer(id("lesser_spirit"), "main")

        fun registerAttributes() {
            FabricDefaultAttributeRegistry.register(LESSER_SPIRIT, DefaultAttributeContainer.builder().add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0))
        }

        @Environment(EnvType.CLIENT)
        fun registerRenderers() {
            EntityRendererRegistry.register(LESSER_SPIRIT, EntityRendererFactory {
                return@EntityRendererFactory LesserSpiritRenderer(it)
            })

            EntityModelLayerRegistry.registerModelLayer(LESSER_SPIRIT_LAYER, LesserSpiritModel::texturedModelData)
        }
    }
}