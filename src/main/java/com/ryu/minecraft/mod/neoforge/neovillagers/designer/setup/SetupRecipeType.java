package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupRecipeType {
    
    public static final DeferredRegister<RecipeType<?>> REGISTER = DeferredRegister.create(Registries.RECIPE_TYPE,
            NeoVillagersDesigner.MODID);
    
    public static final DeferredHolder<RecipeType<?>, RecipeType<DesignerRecipe>> DESIGNER = SetupRecipeType.REGISTER
            .register(DesignerRecipe.RECIPE_NAME,
                    () -> RecipeType.simple(new ResourceLocation(DesignerRecipe.RECIPE_NAME)));
    
    private SetupRecipeType() {
        
    }
}
