package com.ryu.minecraft.mod.neoforge.neovillagers.designer.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DecorCauldronBlock extends Block {
    
    public static final String BLOCK_NAME = "decoration_cauldron";
    
    private static final VoxelShape INSIDE = Block.box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    
    protected static final VoxelShape SHAPE = Shapes.join(Shapes.block(),
            Shapes.or(Block.box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), Block.box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D),
                    Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), DecorCauldronBlock.INSIDE),
            BooleanOp.ONLY_FIRST);
    
    public DecorCauldronBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public VoxelShape getInteractionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return DecorCauldronBlock.INSIDE;
    }
    
    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        return DecorCauldronBlock.SHAPE;
    }
}
