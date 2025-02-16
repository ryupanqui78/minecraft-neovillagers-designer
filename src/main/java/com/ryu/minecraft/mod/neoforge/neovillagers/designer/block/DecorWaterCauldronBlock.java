package com.ryu.minecraft.mod.neoforge.neovillagers.designer.block;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.helpers.DecorCauldronHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DecorWaterCauldronBlock extends Block {
    
    public static final String BLOCK_NAME = "decoration_water_cauldron";
    
    private static final VoxelShape INSIDE = Block.box(2.0D, 4.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    
    protected static final VoxelShape SHAPE = Shapes.join(Shapes.block(),
            Shapes.or(Block.box(0.0D, 0.0D, 4.0D, 16.0D, 3.0D, 12.0D), Block.box(4.0D, 0.0D, 0.0D, 12.0D, 3.0D, 16.0D),
                    Block.box(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D), DecorWaterCauldronBlock.INSIDE),
            BooleanOp.ONLY_FIRST);
    
    public DecorWaterCauldronBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public VoxelShape getInteractionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return DecorWaterCauldronBlock.INSIDE;
    }
    
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return DecorWaterCauldronBlock.SHAPE;
    }
    
    @Override
    protected InteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (pStack.is(Items.BUCKET)) {
            DecorCauldronHelper.fillBucket(pLevel, pPos, pPlayer, pHand, pStack);
            return InteractionResult.SUCCESS;
        } else if (pStack.is(Items.WATER_BUCKET)) {
            DecorCauldronHelper.fillCauldron(pLevel, pPos, pPlayer, pHand, pStack);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
