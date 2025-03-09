package com.lumiscosity.jumapat.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PaintingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PaintingEntityRenderer.class)
public class PaintingCutoutMixin {
	@Shadow
	public Identifier getTexture(PaintingEntity paintingEntity) {
		return MinecraftClient.getInstance().getPaintingManager().getBackSprite().getAtlasId();
	}

	@ModifyVariable(method = "render(Lnet/minecraft/entity/decoration/painting/PaintingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("STORE"), ordinal = 0)
	private VertexConsumer injected(VertexConsumer x, PaintingEntity paintingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		return vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(this.getTexture(paintingEntity)));
	}
}