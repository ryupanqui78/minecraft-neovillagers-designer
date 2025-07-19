package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.google.common.collect.ImmutableSet;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.Designer;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupVillagers {
    
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister
            .create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, NeoVillagersDesigner.MODID);
    
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister
            .create(BuiltInRegistries.VILLAGER_PROFESSION, NeoVillagersDesigner.MODID);
    
    public static final ResourceKey<VillagerProfession> DESIGNER = SetupVillagers.createKey(Designer.ENTITY_NAME);
    
    public static final DeferredHolder<PoiType, PoiType> DESIGNER_POI = SetupVillagers.POI_TYPES
            .register(Designer.ENTITY_POI_NAME, () -> SetupVillagers.createPoiType(SetupBlocks.DESIGNER));
    
    static {
        SetupVillagers.VILLAGER_PROFESSIONS.register(Designer.ENTITY_NAME, Designer::registerVillager);
    }
    
    private static ResourceKey<VillagerProfession> createKey(String pName) {
        return ResourceKey.create(Registries.VILLAGER_PROFESSION,
                ResourceLocation.fromNamespaceAndPath(NeoVillagersDesigner.MODID, pName));
    }
    
    private static PoiType createPoiType(DeferredBlock<? extends Block> block) {
        return new PoiType(ImmutableSet.copyOf(block.get().getStateDefinition().getPossibleStates()), 1, 1);
    }
    
    public static void register(IEventBus eventBus) {
        SetupVillagers.POI_TYPES.register(eventBus);
        SetupVillagers.VILLAGER_PROFESSIONS.register(eventBus);
    }
    
    private SetupVillagers() {
    }
}
