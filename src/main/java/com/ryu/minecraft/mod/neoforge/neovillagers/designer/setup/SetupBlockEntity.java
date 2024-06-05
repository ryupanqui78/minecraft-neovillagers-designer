package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.entity.DesignerBlockEntity;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory.DesignerMenu;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupBlockEntity {
    
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(BuiltInRegistries.BLOCK_ENTITY_TYPE, NeoVillagersDesigner.MODID);
    
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DesignerBlockEntity>> DESIGNER_TABLE = SetupBlockEntity.BLOCK_ENTITIES
            .register(DesignerMenu.MENU_NAME, () -> BlockEntityType.Builder
                    .of(DesignerBlockEntity::new, SetupBlocks.DESIGNER_TABLE_BLOCK.get()).build(null));
    
    private SetupBlockEntity() {
        
    }
}
