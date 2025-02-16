package com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;

public class DesignerSerialize implements RecipeSerializer<DesignerRecipe> {
    
    private static final String ELEMENT_INGREDIENT = "ingredient";
    private static final String ELEMENT_GROUP = "group";
    private static final String ELEMENT_RESULT = "result";
    
    private final MapCodec<DesignerRecipe> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, DesignerRecipe> streamCodec;
    
    public DesignerSerialize(SingleItemRecipe.Factory<DesignerRecipe> pFactory) {
        this.codec = RecordCodecBuilder.mapCodec(element -> element.group(
                Codec.STRING.optionalFieldOf(DesignerSerialize.ELEMENT_GROUP, "").forGetter(DesignerRecipe::group),
                Ingredient.CODEC.fieldOf(DesignerSerialize.ELEMENT_INGREDIENT).forGetter(DesignerRecipe::input),
                ItemStack.STRICT_CODEC.fieldOf(DesignerSerialize.ELEMENT_RESULT).forGetter(DesignerRecipe::getResult))
                .apply(element, pFactory::create));
        this.streamCodec = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, DesignerRecipe::group,
                Ingredient.CONTENTS_STREAM_CODEC, DesignerRecipe::input, ItemStack.STREAM_CODEC,
                DesignerRecipe::getResult, pFactory::create);
    }
    
    @Override
    public MapCodec<DesignerRecipe> codec() {
        return this.codec;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, DesignerRecipe> streamCodec() {
        return this.streamCodec;
    }
    
}
