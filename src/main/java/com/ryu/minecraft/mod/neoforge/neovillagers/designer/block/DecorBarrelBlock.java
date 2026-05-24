package com.ryu.minecraft.mod.neoforge.neovillagers.designer.block;

import java.util.stream.Stream;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.helpers.DecoratorHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DecorBarrelBlock extends Block {
    
    public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;
    
    private static final VoxelShape SHAPE_UP = Stream
            .of(Shapes.box(0.0625, 0.00625, 0.0625, 0.9375, 0.99375, 0.9375),
                    Shapes.box(0, 0, 0.0625, 0.0625, 1, 0.9375), Shapes.box(0.9375, 0, 0.0625, 1, 1, 0.9375),
                    Shapes.box(0.0625, 0, 0, 0.9375, 1, 0.0625), Shapes.box(0.0625, 0, 0.9375, 0.9375, 1, 1))
            .reduce(Shapes.empty(), (a, b) -> Shapes.join(a, b, BooleanOp.OR));
    private static final VoxelShape SHAPE_DOWN = DecoratorHelper.rotateVertical(DecorBarrelBlock.SHAPE_UP, true);
    private static final VoxelShape SHAPE_NORTH = DecoratorHelper.rotateVertical(DecorBarrelBlock.SHAPE_UP, false);
    private static final VoxelShape SHAPE_SOUTH = DecoratorHelper.rotateShape(Direction.NORTH, Direction.SOUTH,
            DecorBarrelBlock.SHAPE_NORTH);
    private static final VoxelShape SHAPE_EAST = DecoratorHelper.rotateShape(Direction.NORTH, Direction.EAST,
            DecorBarrelBlock.SHAPE_NORTH);
    private static final VoxelShape SHAPE_WEST = DecoratorHelper.rotateShape(Direction.NORTH, Direction.WEST,
            DecorBarrelBlock.SHAPE_NORTH);
    
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
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(DecorBarrelBlock.FACING)) {
        case DOWN -> DecorBarrelBlock.SHAPE_DOWN;
        case UP -> DecorBarrelBlock.SHAPE_UP;
        case NORTH -> DecorBarrelBlock.SHAPE_NORTH;
        case SOUTH -> DecorBarrelBlock.SHAPE_SOUTH;
        case EAST -> DecorBarrelBlock.SHAPE_EAST;
        case WEST -> DecorBarrelBlock.SHAPE_WEST;
        };
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(DecorBarrelBlock.FACING,
                context.getNearestLookingDirection().getOpposite());
    }
    
}
