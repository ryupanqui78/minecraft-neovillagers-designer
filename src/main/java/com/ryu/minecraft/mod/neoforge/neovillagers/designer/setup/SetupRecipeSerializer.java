package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerRecipe;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerSerialize;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupRecipeSerializer {
    
    public static final DeferredRegister<RecipeSerializer<?>> REGISTER = DeferredRegister
            .create(Registries.RECIPE_SERIALIZER, NeoVillagersDesigner.MODID);
    
    public static final DeferredHolder<RecipeSerializer<?>, DesignerSerialize> DESIGNER = SetupRecipeSerializer.REGISTER
            .register(DesignerRecipe.RECIPE_NAME, () -> new DesignerSerialize(DesignerRecipe::new));
    
    private SetupRecipeSerializer() {
        
    }
}
