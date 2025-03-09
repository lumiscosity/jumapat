package com.lumiscosity.jumapat.mixin.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndCrystalEntityRenderer.class)
public class EndCrystalTransparentMixin {
	@Shadow @Final private static RenderLayer END_CRYSTAL;

	@Redirect(method = "<clinit>", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/render/RenderLayer;getEntityCutoutNoCull(Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;"
	))
	private static RenderLayer injected(Identifier texture) {
		return RenderLayer.getEntityTranslucent(texture);
	}
}