package com.lumiscosity.jumapat.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemFrameEntityRenderer.class)
public class ItemFrameTransparentMixin {
	private VertexConsumerProvider vcp;

	@Inject(method = "render(Lnet/minecraft/entity/decoration/ItemFrameEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"))
	private void fetch(CallbackInfo ci, @Local(argsOnly = true) VertexConsumerProvider vertexConsumerProvider) {
		vcp = vertexConsumerProvider;
	}

	@ModifyArg(method = "render(Lnet/minecraft/entity/decoration/ItemFrameEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/render/block/BlockModelRenderer;render(Lnet/minecraft/client/util/math/MatrixStack$Entry;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/block/BlockState;Lnet/minecraft/client/render/model/BakedModel;FFFII)V"
			),
			index = 1)
	private VertexConsumer injected(VertexConsumer vertexConsumer) {
		return vcp.getBuffer(TexturedRenderLayers.getEntityTranslucentCull());
	}
}