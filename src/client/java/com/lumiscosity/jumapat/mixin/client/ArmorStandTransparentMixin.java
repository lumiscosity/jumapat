package com.lumiscosity.jumapat.mixin.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ArmorStandEntityRenderer.class)
public class ArmorStandTransparentMixin {
	// FIXME: this does nothing
	//@Redirect(method = "getRenderLayer(Lnet/minecraft/entity/decoration/ArmorStandEntity;ZZZ)Lnet/minecraft/client/render/RenderLayer;",
//			at = @At(
//					value = "INVOKE",
//					target = "Lnet/minecraft/client/render/RenderLayer;getEntityCutoutNoCull(Lnet/minecraft/util/Identifier;Z)Lnet/minecraft/client/render/RenderLayer;"
//			)
//	)
//	private RenderLayer injected(Identifier texture, boolean affectsOutline) {
//		return RenderLayer.getEntityTranslucent(texture, affectsOutline);
//	}
}