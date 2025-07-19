package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

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
    private static final String DECORATION_COMPOSTER_BLOCK_NAME = "decoration_composter";
    
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoVillagersDesigner.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NeoVillagersDesigner.MODID);
    
    // Block
    public static final DeferredBlock<DesignerBlock> DESIGNER = SetupBlocks
            .registerSingleBlock(DesignerBlock.BLOCK_NAME, DesignerBlock::new, 2.5f);
    
    // Register Single blocks
    public static final DeferredBlock<DecorBarrelBlock> DECOR_BARREL_BLOCK = SetupBlocks
            .registerSingleBlock(DecorBarrelBlock.BLOCK_NAME, DecorBarrelBlock::new, 1.5f);
    public static final DeferredBlock<Block> DECOR_BOX_CROSS_BLOCK = SetupBlocks
            .registerSingleBlock(SetupBlocks.DECORATION_BOX_CROSS_BLOCK_NAME, Block::new, 0.8f);
    public static final DeferredBlock<Block> DECOR_BOX_FIX_BLOCK = SetupBlocks
            .registerSingleBlock(SetupBlocks.DECORATION_BOX_FIX_BLOCK_NAME, Block::new, 0.8f);
    public static final DeferredBlock<Block> DECOR_BOX_BLOCK = SetupBlocks
            .registerSingleBlock(SetupBlocks.DECORATION_BOX_BLOCK_NAME, Block::new, 0.8f);
    public static final DeferredBlock<DecorCauldronBlock> DECOR_CAULDRON_BLOCK = SetupBlocks
            .registerSingleBlock(DecorCauldronBlock.BLOCK_NAME, DecorCauldronBlock::new, 2.0f);
    public static final DeferredBlock<DecorChestBlock> DECOR_CHEST_BLOCK = SetupBlocks
            .registerSingleBlock(DecorChestBlock.BLOCK_NAME, DecorChestBlock::new, 1.2f);
    public static final DeferredBlock<Block> DECOR_COMPOSTER_BLOCK = SetupBlocks
            .registerSingleBlock(SetupBlocks.DECORATION_COMPOSTER_BLOCK_NAME, Block::new, 0.7f);
    public static final DeferredBlock<DecorWaterCauldronBlock> DECOR_WATER_CAULDRON_BLOCK = SetupBlocks
            .registerSingleBlock(DecorWaterCauldronBlock.BLOCK_NAME, DecorWaterCauldronBlock::new, 2.0f);
    
    private static <B extends Block> DeferredBlock<B> registerSingleBlock(String pName, Function<BlockBehaviour.Properties, ? extends B> func, float pStrength) {
        final DeferredBlock<B> block = SetupBlocks.BLOCKS.registerBlock(pName, func,
                BlockBehaviour.Properties.of().strength(pStrength).requiresCorrectToolForDrops());
        SetupBlocks.ITEMS.registerSimpleBlockItem(block);
        return block;
    }
    
    private SetupBlocks() {
    }
}
