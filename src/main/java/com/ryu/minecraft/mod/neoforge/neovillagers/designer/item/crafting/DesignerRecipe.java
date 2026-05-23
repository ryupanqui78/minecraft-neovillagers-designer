package com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting;

import java.util.List;

import com.mojang.serialization.MapCodec;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.display.SingleRecipeDisplay;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupRecipeType;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public class DesignerRecipe extends SingleItemRecipe {
    
    public static final MapCodec<DesignerRecipe> MAP_CODEC = SingleItemRecipe.simpleMapCodec(DesignerRecipe::new);
    public static final StreamCodec<RegistryFriendlyByteBuf, DesignerRecipe> STREAM_CODEC = SingleItemRecipe
            .simpleStreamCodec(DesignerRecipe::new);
    public static final RecipeSerializer<DesignerRecipe> SERIALIZER = new RecipeSerializer<>(DesignerRecipe.MAP_CODEC,
            DesignerRecipe.STREAM_CODEC);
    
    public DesignerRecipe(Recipe.CommonInfo commonInfo, Ingredient ingredient, ItemStackTemplate result) {
        super(commonInfo, ingredient, result);
    }
    
    @Override
    public List<RecipeDisplay> display() {
        return List
                .of(new SingleRecipeDisplay(this.input().display(), new SlotDisplay.ItemStackSlotDisplay(this.result()),
                        new SlotDisplay.ItemSlotDisplay(SetupBlocks.DESIGNER.asItem())));
    }
    
    public ItemStack getResultItem() {
        return this.result().create();
    }
    
    @Override
    public RecipeSerializer<DesignerRecipe> getSerializer() {
        return DesignerRecipe.SERIALIZER;
    }
    
    @Override
    public RecipeType<DesignerRecipe> getType() {
        return SetupRecipeType.DESIGNER.get();
    }
    
    @Override
    public String group() {
        return "";
    }
    
    @Override
    public boolean isSpecial() {
        return true;
    }
    
    @Override
    public RecipeBookCategory recipeBookCategory() {
        return SetupRecipeType.DESIGNER_CATEGORY.get();
    }
    
}
