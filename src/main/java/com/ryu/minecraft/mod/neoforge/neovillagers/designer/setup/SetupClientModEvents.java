package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;

import net.minecraft.client.renderer.BiomeColors;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod.EventBusSubscriber(modid = NeoVillagersDesigner.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupClientModEvents {
    
    private static final int WATER_COLOR_BLUE = -12827478;
    
    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        event.register((state, blockAndTint, blockPos, color) -> (blockAndTint != null) && (blockPos != null)
                ? BiomeColors.getAverageWaterColor(blockAndTint, blockPos)
                : -1, SetupBlocks.DECOR_WATER_CAULDRON_BLOCK.get());
    }
    
    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> SetupClientModEvents.WATER_COLOR_BLUE,
                SetupBlocks.DECOR_WATER_CAULDRON_BLOCK.get().asItem());
    }
    
    private SetupClientModEvents() {
        
    }
}
