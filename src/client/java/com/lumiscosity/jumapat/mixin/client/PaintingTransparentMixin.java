package com.lumiscosity.jumapat.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PaintingEntityRenderer;
import net.minecraft.client.render.entity.state.PaintingEntityRenderState;
import net.minecraft.client.texture.PaintingManager;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PaintingEntityRenderer.class)
public class PaintingTransparentMixin {
	@ModifyVariable(method = "render(Lnet/minecraft/client/render/entity/state/PaintingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("STORE"), ordinal = 0)
	private VertexConsumer injected(VertexConsumer x, PaintingEntityRenderState paintingEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		PaintingManager paintingManager = MinecraftClient.getInstance().getPaintingManager();
		Sprite sprite = paintingManager.getBackSprite();
		return vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(sprite.getAtlasId()));
	}
}