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
    
    protected final SingleItemRecipe.Factory<DesignerRecipe> factory;
    
    public DesignerSerialize(SingleItemRecipe.Factory<DesignerRecipe> pFactory) {
        this.factory = pFactory;
    }
    
    @Override
    public MapCodec<DesignerRecipe> codec() {
        return RecordCodecBuilder.mapCodec(elements -> elements.group(
                Codec.STRING.optionalFieldOf(DesignerSerialize.ELEMENT_GROUP, "").forGetter(DesignerRecipe::getGroup),
                Ingredient.CODEC_NONEMPTY.fieldOf(DesignerSerialize.ELEMENT_INGREDIENT)
                        .forGetter(DesignerRecipe::getIngredient),
                ItemStack.STRICT_CODEC.fieldOf(DesignerSerialize.ELEMENT_RESULT).forGetter(DesignerRecipe::getResult))
                .apply(elements, this.factory::create));
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, DesignerRecipe> streamCodec() {
        return StreamCodec.composite(ByteBufCodecs.STRING_UTF8, DesignerRecipe::getGroup,
                Ingredient.CONTENTS_STREAM_CODEC, DesignerRecipe::getIngredient, ItemStack.STREAM_CODEC,
                DesignerRecipe::getResult, this.factory::create);
    }
    
}
