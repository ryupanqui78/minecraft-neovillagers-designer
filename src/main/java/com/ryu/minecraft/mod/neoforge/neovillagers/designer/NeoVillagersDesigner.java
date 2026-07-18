package com.ryu.minecraft.mod.neoforge.neovillagers.designer;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.client.ClientDesignerRecipes;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.client.ClientboundDesignerRecipesPayload;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.server.ServerDesignerRecipes;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupCreativeModTab;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupMenus;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupRecipeType;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupVillagers;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(NeoVillagersDesigner.MODID)
public class NeoVillagersDesigner {
    
    public static final String MODID = "neovillagersdesigner";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    private static final String PAYLOAD_VERSION = "1";
    
    public NeoVillagersDesigner(IEventBus modEventBus, ModContainer modContainer) {
        NeoVillagersDesigner.LOGGER.debug("Loading NeoVillagers Designer mod");
        SetupBlocks.BLOCKS.register(modEventBus);
        SetupBlocks.ITEMS.register(modEventBus);
        
        SetupCreativeModTab.CREATIVE_MODE_TABS.register(modEventBus);
        SetupMenus.MENUS.register(modEventBus);
        SetupVillagers.register(modEventBus);
        
        NeoForge.EVENT_BUS.addListener(ServerDesignerRecipes::onDatapackSync);
        
        SetupRecipeType.RECIPE_DISPLAYS.register(modEventBus);
        SetupRecipeType.RECIPE_BOOK_CATEGORIES.register(modEventBus);
        SetupRecipeType.RECIPE_TYPES.register(modEventBus);
        SetupRecipeType.RECIPE_SERIALIZERS.register(modEventBus);
        
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerPayloadHandlersEvent);
    }
    
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(SetupBlocks.DESIGNER);
        }
    }
    
    public void registerPayloadHandlersEvent(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(PAYLOAD_VERSION);
        registrar.playToClient(ClientboundDesignerRecipesPayload.TYPE_PAYLOAD,
                ClientboundDesignerRecipesPayload.STREAM_CODEC, ClientDesignerRecipes::handle);
    }
}
