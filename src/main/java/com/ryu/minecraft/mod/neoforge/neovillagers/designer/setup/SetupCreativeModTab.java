package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupCreativeModTab {
    
    private static final String TAB_NAME_DECORATION = "decoration_tab";
    
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, NeoVillagersDesigner.MODID);
    
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECORATION_TAB = SetupCreativeModTab.CREATIVE_MODE_TABS
            .register(SetupCreativeModTab.TAB_NAME_DECORATION,
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.neovillagersdesigner.decoration"))
                            .withTabsBefore(CreativeModeTabs.REDSTONE_BLOCKS)
                            .icon(SetupBlocks.DECOR_BARREL_BLOCK::toStack).displayItems((parameters, output) -> {
                                output.accept(SetupBlocks.DECOR_BARREL_BLOCK);
                                output.accept(SetupBlocks.DECOR_BOX_CROSS_BLOCK);
                                output.accept(SetupBlocks.DECOR_BOX_FIX_BLOCK);
                                output.accept(SetupBlocks.DECOR_BOX_BLOCK);
                                output.accept(SetupBlocks.DECOR_CAULDRON_BLOCK);
                                output.accept(SetupBlocks.DECOR_CHEST_BLOCK);
                                output.accept(SetupBlocks.DECOR_COMPOSTER_BLOCK);
                                output.accept(SetupBlocks.DECOR_WATER_CAULDRON_BLOCK);
                            }).build());
    
    private SetupCreativeModTab() {
    }
}
