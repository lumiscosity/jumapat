package com.lumiscosity.jumapat.mixin.client;

import com.lumiscosity.jumapat.MconfGetter;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ConfigReaderMixinPlugin implements IMixinConfigPlugin {
    public HashMap<String, String> options = HashMap.newHashMap(4);
    File config = FabricLoader.getInstance().getConfigDir().resolve("jumapat.mcf").toFile();
    public static final Logger LOGGER = LoggerFactory.getLogger("jumapat");

    @Override
    public void onLoad(String s) {
        if (!config.exists()) {
            try {
                FileWriter writer = new FileWriter(config);
                writer.write("PAINTING=TRANSPARENT\n");
                writer.write("ITEM_FRAME=TRANSPARENT\n");
                writer.write("END_CRYSTAL=TRANSPARENT\n");
                //writer.write("ARMOR_STAND=TRANSPARENT\n");
                writer.close();
            } catch (IOException ignored) {
            }
        }

        options.put("PAINTING", MconfGetter.get(config, "PAINTING"));
        options.put("ITEM_FRAME", MconfGetter.get(config, "ITEM_FRAME"));
        options.put("END_CRYSTAL", MconfGetter.get(config, "END_CRYSTAL"));
        //options.put("ARMOR_STAND", MconfGetter.get(config, "ARMOR_STAND"));
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return switch (mixinClassName) {
            case "com.lumiscosity.jumapat.mixin.client.PaintingTransparentMixin" -> Objects.equals(options.get("PAINTING"), "TRANSPARENT");
            case "com.lumiscosity.jumapat.mixin.client.ItemFrameTransparentMixin" -> Objects.equals(options.get("ITEM_FRAME"), "TRANSPARENT");
            case "com.lumiscosity.jumapat.mixin.client.EndCrystalTransparentMixin" -> Objects.equals(options.get("END_CRYSTAL"), "TRANSPARENT");
            //case "com.lumiscosity.jumapat.mixin.client.ArmorStandTransparentMixin" -> Objects.equals(options.get("ARMOR_STAND"), "TRANSPARENT");
            case "com.lumiscosity.jumapat.mixin.client.PaintingCutoutMixin" -> Objects.equals(options.get("PAINTING"), "CUTOUT");
            case "com.lumiscosity.jumapat.mixin.client.ItemFrameCutoutMixin" -> Objects.equals(options.get("ITEM_FRAME"), "CUTOUT");
            default -> false;
        };
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }
}
