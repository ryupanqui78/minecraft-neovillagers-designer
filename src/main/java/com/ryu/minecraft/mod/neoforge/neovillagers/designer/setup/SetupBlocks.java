package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupBlocks {
    
    private static final String DECORATION_BOX_CROSS_BLOCK_NAME = "decoration_box_cross";
    private static final String DECORATION_BOX_FIX_BLOCK_NAME = "decoration_box_fix";
    
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoVillagersDesigner.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NeoVillagersDesigner.MODID);
    
    // Register Single blocks
    public static final DeferredBlock<Block> DECOR_BOX_CROSS_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            SetupBlocks.DECORATION_BOX_CROSS_BLOCK_NAME, Block::new,
            BlockBehaviour.Properties.of().strength(0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> DECOR_BOX_FIX_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            SetupBlocks.DECORATION_BOX_FIX_BLOCK_NAME, Block::new,
            BlockBehaviour.Properties.of().strength(0.8F).requiresCorrectToolForDrops());
    
    // Register Single block Items
    public static final DeferredItem<BlockItem> DECOR_BOX_CROSS_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_BOX_CROSS_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_BOX_FIX_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_BOX_FIX_BLOCK);
    
    private SetupBlocks() {
    }
}
