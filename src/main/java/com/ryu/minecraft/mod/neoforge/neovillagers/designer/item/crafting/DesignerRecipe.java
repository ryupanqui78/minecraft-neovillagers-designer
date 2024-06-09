package com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupRecipeSerializer;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupRecipeType;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;

public class DesignerRecipe extends SingleItemRecipe {
    
    public static final String RECIPE_NAME = "designer";
    
    public DesignerRecipe(String pGroup, Ingredient pIngredient, ItemStack pResult) {
        super(SetupRecipeType.DESIGNER.get(), SetupRecipeSerializer.DESIGNER.get(), pGroup, pIngredient, pResult);
    }
    
    public Ingredient getIngredient() {
        return this.ingredient;
    }
    
    public ItemStack getResult() {
        return this.result;
    }
    
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(SetupBlocks.DESIGNER_TABLE_BLOCK.get());
    }
    
    @Override
    public RecipeType<?> getType() {
        return SetupRecipeType.DESIGNER.get();
    }
    
    @Override
    public boolean isSpecial() {
        return true;
    }
    
    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return this.ingredient.test(pContainer.getItem(0));
    }
    
}
