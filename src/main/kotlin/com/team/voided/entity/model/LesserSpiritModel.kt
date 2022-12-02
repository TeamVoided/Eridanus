package com.team.voided.entity.model

import com.team.voided.entity.LesserSpirit
import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack


class LesserSpiritModel(root: ModelPart) : EntityModel<LesserSpirit>() {
    private val head: ModelPart
    private val body: ModelPart
    private val rightArm: ModelPart
    private val leftArm: ModelPart

    init {
        head = root.getChild("head")
        body = root.getChild("body")
        rightArm = root.getChild("right_arm")
        leftArm = root.getChild("left_arm")
    }

    override fun setAngles(entity: LesserSpirit, limbSwing: Float, limbSwingAmount: Float, ageInTicks: Float, netHeadYaw: Float, headPitch: Float) { }

    override fun render(
        matrices: MatrixStack,
        vertexConsumer: VertexConsumer,
        light: Int,
        overlay: Int,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float
    ) {
        head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha)
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha)
        rightArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha)
        leftArm.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha)
    }

    companion object {
        val texturedModelData: TexturedModelData
            get() {
                val modelData = ModelData()
                val modelPartData = modelData.root
                val head = modelPartData.addChild(
                    "head",
                    ModelPartBuilder.create().uv(0, 0).cuboid(-2.5f, -4.0f, -2.5f, 5.0f, 5.0f, 5.0f, Dilation(0.0f)),
                    ModelTransform.pivot(0.0f, 18.0f, 0.0f)
                )
                val body = modelPartData.addChild(
                    "body",
                    ModelPartBuilder.create().uv(0, 10).cuboid(-1.5f, 1.0f, -1.0f, 3.0f, 4.0f, 2.0f, Dilation(0.0f))
                        .uv(-13, 16).cuboid(-1.5f, 1.0f, -1.0f, 3.0f, 5.0f, 2.0f, Dilation(-0.2f)),
                    ModelTransform.pivot(0.0f, 18.0f, 0.0f)
                )
                val rightArm = modelPartData.addChild(
                    "right_arm",
                    ModelPartBuilder.create().uv(23, 0).cuboid(-0.75f, 0.5f, -1.0f, 1.0f, 4.0f, 2.0f, Dilation(0.0f)),
                    ModelTransform.pivot(-1.75f, 18.5f, 0.0f)
                )
                val leftArm = modelPartData.addChild(
                    "left_arm",
                    ModelPartBuilder.create().uv(23, 6).cuboid(-0.25f, 0.5f, -1.0f, 1.0f, 4.0f, 2.0f, Dilation(0.0f)),
                    ModelTransform.pivot(1.75f, 18.5f, 0.0f)
                )
                return TexturedModelData.of(modelData, 32, 32)
            }
    }
}