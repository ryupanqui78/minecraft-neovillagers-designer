package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import java.util.function.Supplier;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupRecipeType {
    
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE,
            NeoVillagersDesigner.MODID);
    
    public static final Supplier<RecipeType<DesignerRecipe>> DESIGNER = SetupRecipeType.RECIPE_TYPES
            .register(DesignerRecipe.RECIPE_NAME, () -> RecipeType.<DesignerRecipe> simple(
                    ResourceLocation.fromNamespaceAndPath(NeoVillagersDesigner.MODID, DesignerRecipe.RECIPE_NAME)));
    
    private SetupRecipeType() {
        
    }
}
