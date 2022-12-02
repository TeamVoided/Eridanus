package com.team.voided.entity.renderer

import com.team.voided.entity.EridanusEntities
import com.team.voided.entity.LesserSpirit
import com.team.voided.entity.model.LesserSpiritModel
import com.team.voided.id
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.util.Identifier

class LesserSpiritRenderer(context: EntityRendererFactory.Context) :
    LivingEntityRenderer<LesserSpirit, LesserSpiritModel>(context, LesserSpiritModel(context.getPart(EridanusEntities.LESSER_SPIRIT_LAYER)), 0f) {
    override fun getTexture(entity: LesserSpirit): Identifier
        = id("textures/entity/lesser_spirit/lesser_spirit.png")
}