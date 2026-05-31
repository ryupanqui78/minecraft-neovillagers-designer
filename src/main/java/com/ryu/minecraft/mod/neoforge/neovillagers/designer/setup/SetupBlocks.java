package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import java.util.List;
import java.util.function.Function;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorBarrelBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorCauldronBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorChestBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DecorWaterCauldronBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DesignerBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupBlocks {
    
    private static final String DECORATION_BOX_CROSS_BLOCK_NAME = "decoration_box_cross";
    private static final String DECORATION_BOX_FIX_BLOCK_NAME = "decoration_box_fix";
    private static final String DECORATION_BOX_BLOCK_NAME = "decoration_box";
    
    private static final List<String> WOOD_TYPES = List.of("acacia", "bamboo", "birch", "cherry", "crimson", "dark_oak",
            "jungle", "mangrove", "oak", "pale_oak", "spruce", "warped");
    
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoVillagersDesigner.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NeoVillagersDesigner.MODID);
    
    // Block
    public static final DeferredBlock<DesignerBlock> DESIGNER = SetupBlocks
            .registerSingleBlock(DesignerBlock.BLOCK_NAME, DesignerBlock::new, 2.5f);
    
    // Register Single blocks
    public static final List<DeferredBlock<DecorBarrelBlock>> LIST_DECOR_BARREL_BLOCKS = SetupBlocks
            .registerDecorBarrelBlocks();
    public static final DeferredBlock<Block> DECOR_BOX_CROSS_BLOCK = SetupBlocks
            .registerSingleBlock(SetupBlocks.DECORATION_BOX_CROSS_BLOCK_NAME, Block::new, 0.8f);
    public static final DeferredBlock<Block> DECOR_BOX_FIX_BLOCK = SetupBlocks
            .registerSingleBlock(SetupBlocks.DECORATION_BOX_FIX_BLOCK_NAME, Block::new, 0.8f);
    public static final DeferredBlock<Block> DECOR_BOX_BLOCK = SetupBlocks
            .registerSingleBlock(SetupBlocks.DECORATION_BOX_BLOCK_NAME, Block::new, 0.8f);
    public static final DeferredBlock<DecorCauldronBlock> DECOR_CAULDRON_BLOCK = SetupBlocks
            .registerSingleBlock(DecorCauldronBlock.BLOCK_NAME, DecorCauldronBlock::new, 2.0f);
    public static final List<DeferredBlock<DecorChestBlock>> LIST_DECOR_CHEST_BLOCKS = registerDecorChestBlocks();
    public static final List<DeferredBlock<Block>> LIST_DECOR_COMPOSTER_BLOCKS = SetupBlocks
            .registerDecorCompostertBlocks();
    public static final DeferredBlock<DecorWaterCauldronBlock> DECOR_WATER_CAULDRON_BLOCK = SetupBlocks
            .registerSingleBlock(DecorWaterCauldronBlock.BLOCK_NAME, DecorWaterCauldronBlock::new, 2.0f);
    
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
                () -> BlockBehaviour.Properties.of().strength(pStrength).requiresCorrectToolForDrops());
        SetupBlocks.ITEMS.registerSimpleBlockItem(block);
        return block;
    }
    
    private SetupBlocks() {
    }
}
