package com.ryu.minecraft.mod.neoforge.neovillagers.designer;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupCreativeModTab;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(NeoVillagersDesigner.MODID)
public class NeoVillagersDesigner {
    
    public static final String MODID = "neovillagersdesigner";
    
    public NeoVillagersDesigner(IEventBus modEventBus) {
        SetupBlocks.ITEMS.register(modEventBus);
        SetupBlocks.BLOCKS.register(modEventBus);
        
        SetupCreativeModTab.CREATIVE_MODE_TABS.register(modEventBus);
        
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }
    
    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(SetupBlocks.DESIGNER_TABLE_BLOCK);
        }
    }
}
