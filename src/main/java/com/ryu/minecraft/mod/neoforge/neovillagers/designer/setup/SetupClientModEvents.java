package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.client.DesignerScreen;

import net.minecraft.client.renderer.BiomeColors;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = NeoVillagersDesigner.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SetupClientModEvents {
    
    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        event.register((state, blockAndTint, blockPos, color) -> (blockAndTint != null) && (blockPos != null)
                ? BiomeColors.getAverageWaterColor(blockAndTint, blockPos)
                : -1, SetupBlocks.DECOR_WATER_CAULDRON_BLOCK.get());
    }
    
    @SubscribeEvent
    public static void registerMenuSreen(RegisterMenuScreensEvent event) {
        event.register(SetupMenus.DESIGNER.get(), DesignerScreen::new);
    }
    
    private SetupClientModEvents() {
        
    }
}
