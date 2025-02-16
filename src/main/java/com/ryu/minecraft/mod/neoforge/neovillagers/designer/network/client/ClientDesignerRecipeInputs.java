package com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.client;

import java.util.List;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerRecipe;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.DesignerRecipeInputs;

import net.minecraft.world.item.crafting.RecipeHolder;

public record ClientDesignerRecipeInputs(List<RecipeHolder<DesignerRecipe>> recipes) implements DesignerRecipeInputs {
    
}
