package com.lumiscosity.jumapat.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.client.render.entity.state.ItemFrameEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemFrameEntityRenderer.class)
public class ItemFrameCutoutMixin {
	private VertexConsumerProvider vcp;

	@Inject(method = "render(Lnet/minecraft/client/render/entity/state/ItemFrameEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"))
	private void fetch(CallbackInfo ci, @Local(argsOnly = true) VertexConsumerProvider vertexConsumerProvider) {
		vcp = vertexConsumerProvider;
	}

	@Inject(method = "render(Lnet/minecraft/client/render/entity/state/ItemFrameEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V", shift = At.Shift.AFTER))
	private void not_making_a_new_render_layer_for_this(CallbackInfo ci, @Local(argsOnly = true) MatrixStack matrixStack, @Local(argsOnly = true) ItemFrameEntityRenderState itemFrameEntityRenderState) {
		Direction direction = itemFrameEntityRenderState.facing;
		matrixStack.translate((double)direction.getOffsetX() * (double)0.0001F, (double)direction.getOffsetY() * (double)0.0001F, (double)direction.getOffsetZ() * (double)0.0001F);
	}

	@ModifyArg(method = "render(Lnet/minecraft/client/render/entity/state/ItemFrameEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/render/block/BlockModelRenderer;render(Lnet/minecraft/client/util/math/MatrixStack$Entry;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/block/BlockState;Lnet/minecraft/client/render/model/BakedModel;FFFII)V"
			),
			index = 1)
	private VertexConsumer injected(VertexConsumer vertexConsumer) {
		return vcp.getBuffer(TexturedRenderLayers.getEntityCutout());
	}
}