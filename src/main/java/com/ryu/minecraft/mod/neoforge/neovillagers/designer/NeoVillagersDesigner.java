package com.ryu.minecraft.mod.neoforge.neovillagers.designer;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlockEntity;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupCreativeModTab;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupGeneralEvents;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupMenus;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupRecipeSerializer;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupRecipeType;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupVillagers;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(NeoVillagersDesigner.MODID)
public class NeoVillagersDesigner {
    
    public static final String MODID = "neovillagersdesigner";
    
    public NeoVillagersDesigner(IEventBus modEventBus, ModContainer modContainer) {
        SetupBlocks.BLOCKS.register(modEventBus);
        SetupBlocks.ITEMS.register(modEventBus);
        
        SetupCreativeModTab.CREATIVE_MODE_TABS.register(modEventBus);
        
        SetupBlockEntity.BLOCK_ENTITIES.register(modEventBus);
        SetupMenus.MENUS.register(modEventBus);
        
        NeoForge.EVENT_BUS.register(SetupGeneralEvents.class);
        
        SetupVillagers.register(modEventBus);
        
        SetupRecipeType.REGISTER.register(modEventBus);
        SetupRecipeSerializer.REGISTER.register(modEventBus);
        
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }
    
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(SetupBlocks.DESIGNER_TABLE_BLOCK);
        }
    }
}
