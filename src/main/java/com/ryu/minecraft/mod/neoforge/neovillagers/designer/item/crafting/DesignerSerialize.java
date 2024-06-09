package com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class DesignerSerialize implements RecipeSerializer<DesignerRecipe> {
    
    private static final String ELEMENT_INGREDIENT = "ingredient";
    private static final String ELEMENT_GROUP = "group";
    
    @Override
    public Codec<DesignerRecipe> codec() {
        return RecordCodecBuilder.create(elements -> elements
                .group(ExtraCodecs.strictOptionalField(Codec.STRING, DesignerSerialize.ELEMENT_GROUP, "")
                        .forGetter(Recipe::getGroup),
                        Ingredient.CODEC_NONEMPTY.fieldOf(DesignerSerialize.ELEMENT_INGREDIENT)
                                .forGetter(DesignerRecipe::getIngredient),
                        ItemStack.RESULT_CODEC.forGetter(DesignerRecipe::getResult))
                .apply(elements, DesignerRecipe::new));
    }
    
    @Override
    public DesignerRecipe fromNetwork(FriendlyByteBuf pBuffer) {
        final String group = pBuffer.readUtf();
        final Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
        final ItemStack itemstack = pBuffer.readItem();
        return new DesignerRecipe(group, ingredient, itemstack);
    }
    
    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, DesignerRecipe pRecipe) {
        pBuffer.writeUtf(pRecipe.getGroup());
        pRecipe.getIngredient().toNetwork(pBuffer);
        pBuffer.writeItem(pRecipe.getResult());
    }
    
}
