package com.ryu.minecraft.mod.neoforge.neovillagers.designer.block;

import com.mojang.serialization.MapCodec;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.entity.DesignerBlockEntity;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory.DesignerMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

public class DesignerBlock extends BaseEntityBlock {
    
    public static final String BLOCK_NAME = "designer_table";
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final MapCodec<DesignerBlock> CODEC = BlockBehaviour.simpleCodec(DesignerBlock::new);
    
    private static final Component CONTAINER_TITLE = Component.translatable("screen.container.designer");
    
    public DesignerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DesignerBlock.FACING, Direction.NORTH));
    }
    
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return DesignerBlock.CODEC;
    }
    
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(DesignerBlock.FACING);
    }
    
    @Override
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider((pContainerId, playerInv, pAccess) -> new DesignerMenu(pContainerId, playerInv,
                ContainerLevelAccess.create(pLevel, pPos)), DesignerBlock.CONTAINER_TITLE);
    }
    
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(DesignerBlock.FACING, context.getHorizontalDirection().getOpposite());
    }
    
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new DesignerBlockEntity(pPos, pState);
    }
    
    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (!pLevel.isClientSide && (pPlayer instanceof ServerPlayer)) {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            return InteractionResult.CONSUME;
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }
    
}
