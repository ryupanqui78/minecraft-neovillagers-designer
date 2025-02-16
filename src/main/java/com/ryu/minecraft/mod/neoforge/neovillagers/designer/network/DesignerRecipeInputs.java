package com.ryu.minecraft.mod.neoforge.neovillagers.designer.network;

import java.util.List;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerRecipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public interface DesignerRecipeInputs {
    
    List<RecipeHolder<DesignerRecipe>> recipes();
    
    default boolean test(ItemStack pItemStack) {
        return this.recipes().stream().anyMatch(holder -> holder.value().input().test(pItemStack));
    }
}
