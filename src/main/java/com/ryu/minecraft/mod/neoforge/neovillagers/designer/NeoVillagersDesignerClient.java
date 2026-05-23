package com.ryu.minecraft.mod.neoforge.neovillagers.designer;

import java.util.List;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.client.DesignerScreen;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupMenus;

import net.minecraft.client.color.block.BlockTintSources;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = NeoVillagersDesigner.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = NeoVillagersDesigner.MODID, value = Dist.CLIENT)
public class NeoVillagersDesignerClient {
    
    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(BlockTintSources.water()), SetupBlocks.DECOR_WATER_CAULDRON_BLOCK.get());
    }
    
    @SubscribeEvent
    public static void registerMenuSreen(RegisterMenuScreensEvent event) {
        event.register(SetupMenus.DESIGNER.get(), DesignerScreen::new);
    }
    
    public NeoVillagersDesignerClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
    
}
