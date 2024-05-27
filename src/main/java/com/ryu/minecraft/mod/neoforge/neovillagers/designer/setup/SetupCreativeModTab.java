package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupCreativeModTab {
    
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, NeoVillagersDesigner.MODID);
    
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECORATION_TAB = SetupCreativeModTab.CREATIVE_MODE_TABS
            .register("decoration_tab",
                    () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.neovillager.decoration"))
                            .withTabsBefore(CreativeModeTabs.REDSTONE_BLOCKS)
                            .icon(() -> SetupBlocks.DECOR_BOX_CROSS_BLOCK_ITEM.get().getDefaultInstance())
                            .displayItems((parameters, output) -> {
                                output.accept(SetupBlocks.DECOR_BARREL_BLOCK_ITEM.get());
                                output.accept(SetupBlocks.DECOR_BOX_CROSS_BLOCK_ITEM.get());
                                output.accept(SetupBlocks.DECOR_BOX_FIX_BLOCK_ITEM.get());
                                output.accept(SetupBlocks.DECOR_BOX_BLOCK_ITEM.get());
                                output.accept(SetupBlocks.DECOR_CHEST_BLOCK_ITEM.get());
                                output.accept(SetupBlocks.DECOR_COMPOSTER_BLOCK_ITEM.get());
                            }).build());
    
    private SetupCreativeModTab() {
        
    }
}
