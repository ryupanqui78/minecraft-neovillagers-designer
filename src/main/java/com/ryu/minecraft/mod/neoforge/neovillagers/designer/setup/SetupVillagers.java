package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.google.common.collect.ImmutableSet;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.Designer;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupVillagers {
    
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister
            .create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, NeoVillagersDesigner.MODID);
    
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister
            .create(BuiltInRegistries.VILLAGER_PROFESSION, NeoVillagersDesigner.MODID);
    
    public static final DeferredHolder<PoiType, PoiType> DESIGNER_POI = SetupVillagers.POI_TYPES.register(
            Designer.ENTITY_POI_NAME,
            () -> new PoiType(ImmutableSet.copyOf(SetupBlocks.DESIGNER.get().getStateDefinition().getPossibleStates()),
                    1, 1));
    
    public static final DeferredHolder<VillagerProfession, VillagerProfession> DESIGNER = SetupVillagers.VILLAGER_PROFESSIONS
            .register(Designer.ENTITY_NAME,
                    () -> new VillagerProfession(Designer.ENTITY_NAME, x -> x.is(SetupVillagers.DESIGNER_POI.getKey()),
                            x -> x.is(SetupVillagers.DESIGNER_POI.getKey()), ImmutableSet.of(), ImmutableSet.of(),
                            SoundEvents.VILLAGER_CELEBRATE));
    
    public static void register(IEventBus eventBus) {
        SetupVillagers.POI_TYPES.register(eventBus);
        SetupVillagers.VILLAGER_PROFESSIONS.register(eventBus);
    }
    
    private SetupVillagers() {
    }
}
