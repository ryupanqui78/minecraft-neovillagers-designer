package com.ryu.minecraft.mod.neoforge.neovillagers.designer.block;

import java.util.stream.Stream;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.helpers.DecoratorHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Invisible "part" block occupying the left (second) slot of a DecorDoubleChestBlock. It has no model and no drops. When a player breaks it, it delegates
 * destruction to the main chest block so the item is dropped correctly.
 *
 * FACING stores the direction from THIS part block back to the MAIN block.
 */
public class DecorDoubleChestPartBlock extends Block {
    
    /** Direction from this part block back to the main DecorDoubleChestBlock. */
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    
    // The part block occupies X=[0,0.9375], Y=[0,0.875], Z=[0.0625,0.9375]
    // when the chest faces NORTH (part block is to the EAST of the main).
    // Bottom body + lid shapes in the part block's own coordinate space.
    private static final VoxelShape SHAPE_EAST = Stream
            .of(Shapes.box(0, 0, 0.0625, 0.9375, 0.625, 0.9375), Shapes.box(0, 0.5625, 0.0625, 0.9375, 0.875, 0.9375))
            .reduce(Shapes.empty(), (a, b) -> Shapes.join(a, b, BooleanOp.OR));
    private static final VoxelShape SHAPE_SOUTH = DecoratorHelper
            .rotateShape(Direction.EAST, Direction.SOUTH, DecorDoubleChestPartBlock.SHAPE_EAST);
    private static final VoxelShape SHAPE_WEST = DecoratorHelper
            .rotateShape(Direction.EAST, Direction.WEST, DecorDoubleChestPartBlock.SHAPE_EAST);
    private static final VoxelShape SHAPE_NORTH = DecoratorHelper
            .rotateShape(Direction.EAST, Direction.NORTH, DecorDoubleChestPartBlock.SHAPE_EAST);
    
    public DecorDoubleChestPartBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(DecorDoubleChestPartBlock.FACING, Direction.NORTH));
    }
    
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        final BlockPos mainPos = pos.relative(state.getValue(DecorDoubleChestPartBlock.FACING));
        final BlockState mainState = level.getBlockState(mainPos);
        return mainState.getBlock() instanceof DecorDoubleChestBlock;
    }
    
    // ----- Appearance -----
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DecorDoubleChestPartBlock.FACING);
    }
    
    @Override
    public boolean dropFromExplosion(net.minecraft.world.level.Explosion explosion) {
        return false;
    }
    
    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return this.getShape(state);
    }
    
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }
    
    // ----- Survival / automatic removal -----
    
    private VoxelShape getShape(BlockState state) {
        // FACING points back to the main block; the part block is on the OPPOSITE side.
        // When main faces NORTH, part is to the EAST → part block's FACING=WEST
        // The geometry in the part block's space corresponds to the chest's right half,
        // which is the EAST half when the chest faces NORTH.
        return switch (state.getValue(DecorDoubleChestPartBlock.FACING)) {
        // Part is EAST of main (main faces NORTH) → part block's FACING=WEST
        case WEST -> DecorDoubleChestPartBlock.SHAPE_EAST;
        // Part is SOUTH of main (main faces EAST) → part block's FACING=NORTH
        case NORTH -> DecorDoubleChestPartBlock.SHAPE_SOUTH;
        // Part is WEST of main (main faces SOUTH) → part block's FACING=EAST
        case EAST -> DecorDoubleChestPartBlock.SHAPE_WEST;
        // Part is NORTH of main (main faces WEST) → part block's FACING=SOUTH
        case SOUTH -> DecorDoubleChestPartBlock.SHAPE_NORTH;
        default -> DecorDoubleChestPartBlock.SHAPE_EAST;
        };
    }
    
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return this.getShape(state);
    }
    
    // ----- Break delegation -----
    
    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        final BlockPos mainPos = pos.relative(state.getValue(DecorDoubleChestPartBlock.FACING));
        final BlockState mainState = level.getBlockState(mainPos);
        if (mainState.getBlock() instanceof DecorDoubleChestBlock) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
            mainState.getBlock().playerWillDestroy(level, mainPos, mainState, player);
            level.destroyBlock(mainPos, !player.isCreative(), player);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }
    
    // ----- No drops -----
    
    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (directionToNeighbour == state.getValue(DecorDoubleChestPartBlock.FACING)) {
            if (!(neighbourState.getBlock() instanceof DecorDoubleChestBlock)) {
                return Blocks.AIR.defaultBlockState();
            }
        }
        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }
}