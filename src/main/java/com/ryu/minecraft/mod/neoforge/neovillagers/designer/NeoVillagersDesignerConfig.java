package com.ryu.minecraft.mod.neoforge.neovillagers.designer;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = NeoVillagersDesigner.MODID, bus = EventBusSubscriber.Bus.MOD)
public class NeoVillagersDesignerConfig {
    
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    
    static final ModConfigSpec SPEC = BUILDER.build();
    
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        NeoVillagersDesigner.LOGGER.debug("Loading configuration ...");
    }
    
    private NeoVillagersDesignerConfig() {
    }
}
