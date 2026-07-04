package com.ryu.minecraft.mod.neoforge.neovillagers.designer.block;

import java.util.function.Supplier;
import java.util.stream.Stream;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DecorDoubleChestBlock extends DecorChestBlock {
    
    private final Supplier<DecorDoubleChestPartBlock> partBlock;
    
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 14.0D, 14.0D, 14.0D);
    public static final VoxelShape SHAPE_NORTH = Stream.of(Shapes.box(0.9375, 0.5, 0, 1.0625, 0.6875, 0.0625),
            Shapes.box(0.0625, 0, 0.0625, 1, 0.625, 0.9375), Shapes.box(0.0625, 0.5625, 0.0625, 1, 0.875, 0.9375),
            Shapes.box(1, 0, 0.0625, 1.9375, 0.625, 0.9375), Shapes.box(1, 0.5625, 0.0625, 1.9375, 0.875, 0.9375))
            .reduce(Shapes.empty(), (a, b) -> Shapes.join(a, b, BooleanOp.OR));
    private static final VoxelShape SHAPE_SOUTH = rotateHorizontal(SHAPE_NORTH, Direction.NORTH, Direction.SOUTH);
    private static final VoxelShape SHAPE_EAST = rotateHorizontal(SHAPE_NORTH, Direction.NORTH, Direction.EAST);
    private static final VoxelShape SHAPE_WEST = rotateHorizontal(SHAPE_NORTH, Direction.NORTH, Direction.WEST);
    
    public static VoxelShape rotateHorizontal(VoxelShape shape, Direction from, Direction to) {
        VoxelShape[] buffer = new VoxelShape[] { shape, Shapes.empty() };
        
        int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
        
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
                // rotate 90° clockwise around block center
                double nMinX = 1 - maxZ;
                double nMaxX = 1 - minZ;
                double nMinZ = minX;
                double nMaxZ = maxX;
                
                buffer[1] = Shapes.join(buffer[1], Shapes.box(nMinX, minY, nMinZ, nMaxX, maxY, nMaxZ), BooleanOp.OR);
            });
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }
        
        return buffer[0];
    }
    
    public DecorDoubleChestBlock(Supplier<DecorDoubleChestPartBlock> partBlock, Properties properties) {
        super(properties);
        this.partBlock = partBlock;
    }
    
    /**
     * Returns the direction of the second block occupied by this double chest, relative to the block's position. The second half is always to the right of the
     * chest's front face, which is the clockwise direction of FACING.
     */
    public static Direction getNeighborDirection(BlockState state) {
        return state.getValue(FACING).getClockWise();
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state == null)
            return null;
        Direction neighbor = getNeighborDirection(state);
        BlockPos neighborPos = context.getClickedPos().relative(neighbor);
        BlockState neighborState = context.getLevel().getBlockState(neighborPos);
        if (!neighborState.canBeReplaced(context)) {
            return null;
        }
        return state;
    }
    
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction neighbor = getNeighborDirection(state);
        BlockPos neighborPos = pos.relative(neighbor);
        BlockState neighborState = level.getBlockState(neighborPos);
        return neighborState.isAir() || neighborState.canBeReplaced();
    }
    
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        Direction neighborDir = getNeighborDirection(state);
        BlockPos partPos = pos.relative(neighborDir);
        // Place the part block; FACING on the part points BACK to the main block.
        level.setBlock(partPos, partBlock.get().defaultBlockState()
                .setValue(DecorDoubleChestPartBlock.FACING, neighborDir.getOpposite()), Block.UPDATE_ALL);
    }
    
    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        // Remove the part block silently so it doesn't drop anything.
        Direction neighborDir = getNeighborDirection(state);
        BlockPos partPos = pos.relative(neighborDir);
        BlockState partState = level.getBlockState(partPos);
        if (partState.getBlock() instanceof DecorDoubleChestPartBlock) {
            level.setBlock(partPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }
    
    private VoxelShape getShape(BlockState state) {
        return switch (state.getValue(FACING)) {
        case NORTH -> SHAPE_NORTH;
        case SOUTH -> SHAPE_SOUTH;
        case EAST -> SHAPE_EAST;
        case WEST -> SHAPE_WEST;
        default -> SHAPE_NORTH;
        };
    }
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return this.getShape(state);
    }
    
    @Override
    protected VoxelShape getEntityInsideCollisionShape(BlockState state, BlockGetter level, BlockPos pos, Entity entity) {
        return this.getShape(state);
    }
    
    @Override
    protected boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return Block.isShapeFullBlock(this.getShape(state));
    }
    
}