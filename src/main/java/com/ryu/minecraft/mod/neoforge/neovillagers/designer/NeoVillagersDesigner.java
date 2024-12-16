package com.ryu.minecraft.mod.neoforge.neovillagers.designer;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupCreativeModTab;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupMenus;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupRecipeSerializer;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupRecipeType;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupVillagers;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(NeoVillagersDesigner.MODID)
public class NeoVillagersDesigner {
    
    public static final String MODID = "neovillagersdesigner";
    
    public NeoVillagersDesigner(IEventBus modEventBus, ModContainer modContainer) {
        SetupBlocks.BLOCKS.register(modEventBus);
        SetupBlocks.ITEMS.register(modEventBus);
        
        SetupCreativeModTab.CREATIVE_MODE_TABS.register(modEventBus);
        SetupMenus.MENUS.register(modEventBus);
        SetupVillagers.register(modEventBus);
        
        SetupRecipeType.RECIPE_TYPES.register(modEventBus);
        SetupRecipeSerializer.REGISTER.register(modEventBus);
        
        modEventBus.addListener(this::addCreative);
    }
    
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(SetupBlocks.DESIGNER);
        }
    }
}
