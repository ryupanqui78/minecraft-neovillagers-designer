package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorBarrelBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorCauldronBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorChestBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorWaterCauldronBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupBlocks {
    
    private static final String DECORATION_BOX_CROSS_BLOCK_NAME = "decoration_box_cross";
    private static final String DECORATION_BOX_FIX_BLOCK_NAME = "decoration_box_fix";
    private static final String DECORATION_BOX_BLOCK_NAME = "decoration_box";
    private static final String DECORATION_COMPOSTER_BLOCK_NAME = "decoration_composter";
    
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoVillagersDesigner.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NeoVillagersDesigner.MODID);
    
    // Register Single blocks
    public static final DeferredBlock<Block> DECOR_BARREL_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            DecorBarrelBlock.BLOCK_NAME, DecorBarrelBlock::new,
            BlockBehaviour.Properties.of().strength(1.5f).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> DECOR_BOX_CROSS_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            SetupBlocks.DECORATION_BOX_CROSS_BLOCK_NAME, Block::new,
            BlockBehaviour.Properties.of().strength(0.8f).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> DECOR_BOX_FIX_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            SetupBlocks.DECORATION_BOX_FIX_BLOCK_NAME, Block::new,
            BlockBehaviour.Properties.of().strength(0.8f).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> DECOR_BOX_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            SetupBlocks.DECORATION_BOX_BLOCK_NAME, Block::new,
            BlockBehaviour.Properties.of().strength(0.8f).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> DECOR_CHEST_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            DecorChestBlock.BLOCK_NAME, DecorChestBlock::new,
            BlockBehaviour.Properties.of().strength(0.7f).requiresCorrectToolForDrops());
    public static final DeferredBlock<DecorCauldronBlock> DECOR_CAULDRON_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            DecorCauldronBlock.BLOCK_NAME, DecorCauldronBlock::new,
            BlockBehaviour.Properties.of().strength(2F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> DECOR_COMPOSTER_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            SetupBlocks.DECORATION_COMPOSTER_BLOCK_NAME, Block::new,
            BlockBehaviour.Properties.of().strength(1.2f).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> DECOR_WATER_CAULDRON_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            DecorWaterCauldronBlock.BLOCK_NAME, DecorWaterCauldronBlock::new,
            BlockBehaviour.Properties.of().strength(2F).requiresCorrectToolForDrops());
    // Register Single block Items
    public static final DeferredItem<BlockItem> DECOR_BARREL_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_BARREL_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_BOX_CROSS_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_BOX_CROSS_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_BOX_FIX_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_BOX_FIX_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_BOX_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_BOX_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_CAULDRON_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_CAULDRON_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_CHEST_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_CHEST_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_COMPOSTER_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_COMPOSTER_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_WATER_CAULDRON_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_WATER_CAULDRON_BLOCK);
    
    private SetupBlocks() {
    }
}
