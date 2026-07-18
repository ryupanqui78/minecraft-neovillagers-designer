package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import java.util.function.Supplier;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.block.DesignerBlock;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerRecipe;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.display.SingleRecipeDisplay;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupRecipeType {
    
    public static final DeferredRegister<RecipeDisplay.Type<?>> RECIPE_DISPLAYS = DeferredRegister
            .create(BuiltInRegistries.RECIPE_DISPLAY, NeoVillagersDesigner.MODID);
    public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORIES = DeferredRegister
            .create(BuiltInRegistries.RECIPE_BOOK_CATEGORY, NeoVillagersDesigner.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE,
            NeoVillagersDesigner.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
            .create(Registries.RECIPE_SERIALIZER, NeoVillagersDesigner.MODID);
    
    private static final Supplier<RecipeDisplay.Type<SingleRecipeDisplay>> SINGLE_RECIPE_DISPLAY = SetupRecipeType.RECIPE_DISPLAYS
            .register("single_recipe_display", () -> SingleRecipeDisplay.DISPLAY_TYPE);
    
    public static final Supplier<RecipeBookCategory> DESIGNER_CATEGORY = SetupRecipeType.RECIPE_BOOK_CATEGORIES
            .register(DesignerBlock.BLOCK_NAME, RecipeBookCategory::new);
    
    public static final Supplier<RecipeType<DesignerRecipe>> DESIGNER = SetupRecipeType.RECIPE_TYPES
            .register(DesignerBlock.BLOCK_NAME, () -> RecipeType.<DesignerRecipe> simple(
                    Identifier.fromNamespaceAndPath(NeoVillagersDesigner.MODID, DesignerBlock.BLOCK_NAME)));
    
    public static final Supplier<RecipeSerializer<DesignerRecipe>> DESIGNER_SERIALIZER = SetupRecipeType.RECIPE_SERIALIZERS
            .register(DesignerBlock.BLOCK_NAME, () -> DesignerRecipe.SERIALIZER);
    
    private SetupRecipeType() {
        
    }
}
