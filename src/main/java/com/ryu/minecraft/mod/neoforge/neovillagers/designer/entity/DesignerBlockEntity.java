package com.ryu.minecraft.mod.neoforge.neovillagers.designer.entity;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DesignerBlockEntity extends BlockEntity {
    
    public DesignerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SetupBlockEntity.DESIGNER_TABLE.get(), pPos, pBlockState);
    }
}
