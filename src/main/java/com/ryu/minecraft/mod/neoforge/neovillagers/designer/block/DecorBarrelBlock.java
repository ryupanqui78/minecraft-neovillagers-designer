package com.ryu.minecraft.mod.neoforge.neovillagers.designer.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class DecorBarrelBlock extends Block {
    
    public static final String BLOCK_NAME = "decoration_barrel";
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;
    
    public DecorBarrelBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DecorBarrelBlock.FACING, Direction.NORTH));
    }
    
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(DecorBarrelBlock.FACING);
    }
    
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(DecorBarrelBlock.FACING,
                context.getNearestLookingDirection().getOpposite());
    }
    
}
