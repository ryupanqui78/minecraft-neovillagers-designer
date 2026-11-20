package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import java.util.List;
import java.util.function.Function;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorBarrelBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorCauldronBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorChestBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorWaterCauldronBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DesignerBlock;

import net.minecraft.resources.ResourceLocation;
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
    
    private static final List<String> WOOD_TYPES = List.of("acacia", "bamboo", "birch", "cherry", "crimson", "dark_oak",
            "jungle", "mangrove", "oak", "spruce", "warped");
    
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoVillagersDesigner.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NeoVillagersDesigner.MODID);
    
    // Block
    public static final DeferredBlock<Block> DESIGNER = SetupBlocks.BLOCKS.registerBlock(DesignerBlock.BLOCK_NAME,
            DesignerBlock::new, BlockBehaviour.Properties.of().strength(2.5f).requiresCorrectToolForDrops());
    
    // Item
    public static final DeferredItem<BlockItem> DESIGNER_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DESIGNER);
    
    // Register Single blocks
    public static final List<DeferredBlock<DecorBarrelBlock>> LIST_DECOR_BARREL_BLOCKS = SetupBlocks
            .registerDecorBarrelBlocks();
    public static final List<DeferredBlock<DecorChestBlock>> LIST_DECOR_CHEST_BLOCKS = SetupBlocks
            .registerDecorChestBlocks();
    public static final List<DeferredBlock<Block>> LIST_DECOR_COMPOSTER_BLOCKS = SetupBlocks
            .registerDecorCompostertBlocks();
    public static final DeferredBlock<Block> DECOR_BOX_CROSS_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            SetupBlocks.DECORATION_BOX_CROSS_BLOCK_NAME, Block::new,
            BlockBehaviour.Properties.of().strength(0.8f).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> DECOR_BOX_FIX_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            SetupBlocks.DECORATION_BOX_FIX_BLOCK_NAME, Block::new,
            BlockBehaviour.Properties.of().strength(0.8f).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> DECOR_BOX_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            SetupBlocks.DECORATION_BOX_BLOCK_NAME, Block::new,
            BlockBehaviour.Properties.of().strength(0.8f).requiresCorrectToolForDrops());
    public static final DeferredBlock<DecorCauldronBlock> DECOR_CAULDRON_BLOCK = SetupBlocks.BLOCKS.registerBlock(
            DecorCauldronBlock.BLOCK_NAME, DecorCauldronBlock::new,
            BlockBehaviour.Properties.of().strength(2F).requiresCorrectToolForDrops());
    public static final DeferredBlock<DecorWaterCauldronBlock> DECOR_WATER_CAULDRON_BLOCK = SetupBlocks.BLOCKS
            .registerBlock(DecorWaterCauldronBlock.BLOCK_NAME, DecorWaterCauldronBlock::new,
                    BlockBehaviour.Properties.of().strength(2F).requiresCorrectToolForDrops());
    // Register Single block Items
    public static final DeferredItem<BlockItem> DECOR_BOX_CROSS_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_BOX_CROSS_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_BOX_FIX_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_BOX_FIX_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_BOX_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_BOX_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_CAULDRON_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_CAULDRON_BLOCK);
    public static final DeferredItem<BlockItem> DECOR_WATER_CAULDRON_BLOCK_ITEM = SetupBlocks.ITEMS
            .registerSimpleBlockItem(SetupBlocks.DECOR_WATER_CAULDRON_BLOCK);
    
    static {
        SetupBlocks.BLOCKS.addAlias(
                ResourceLocation.fromNamespaceAndPath(NeoVillagersDesigner.MODID, "decoration_barrel"),
                ResourceLocation.fromNamespaceAndPath(NeoVillagersDesigner.MODID, "decoration_barrel_oak"));
        SetupBlocks.BLOCKS.addAlias(
                ResourceLocation.fromNamespaceAndPath(NeoVillagersDesigner.MODID, "decoration_chest"),
                ResourceLocation.fromNamespaceAndPath(NeoVillagersDesigner.MODID, "decoration_chest_oak"));
        SetupBlocks.BLOCKS.addAlias(
                ResourceLocation.fromNamespaceAndPath(NeoVillagersDesigner.MODID, "decoration_composter"),
                ResourceLocation.fromNamespaceAndPath(NeoVillagersDesigner.MODID, "decoration_composter_oak"));
    }
    
    private static List<DeferredBlock<DecorBarrelBlock>> registerDecorBarrelBlocks() {
        return SetupBlocks.WOOD_TYPES.stream().map(woodType -> SetupBlocks
                .registerSingleBlock("decoration_barrel_" + woodType, DecorBarrelBlock::new, 1.5f)).toList();
    }
    
    private static List<DeferredBlock<DecorChestBlock>> registerDecorChestBlocks() {
        return SetupBlocks.WOOD_TYPES.stream().map(
                woodType -> SetupBlocks.registerSingleBlock("decoration_chest_" + woodType, DecorChestBlock::new, 1.2f))
                .toList();
    }
    
    private static List<DeferredBlock<Block>> registerDecorCompostertBlocks() {
        return SetupBlocks.WOOD_TYPES.stream()
                .map(woodType -> SetupBlocks.registerSingleBlock("decoration_composter_" + woodType, Block::new, 1.0f))
                .toList();
    }
    
    private static <B extends Block> DeferredBlock<B> registerSingleBlock(String pName, Function<BlockBehaviour.Properties, ? extends B> func, float pStrength) {
        final DeferredBlock<B> block = SetupBlocks.BLOCKS.registerBlock(pName, func,
                BlockBehaviour.Properties.of().strength(pStrength).requiresCorrectToolForDrops());
        SetupBlocks.ITEMS.registerSimpleBlockItem(block);
        return block;
    }
    
    private SetupBlocks() {
    }
}
