package com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.client;

import java.util.List;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerRecipe;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

public record ClientboundDesignerRecipesPayload(List<RecipeHolder<DesignerRecipe>> recipes) implements CustomPacketPayload {
    
    private static final StreamCodec<RegistryFriendlyByteBuf, RecipeHolder<DesignerRecipe>> HOLDER_STREAM_CODEC = StreamCodec
            .composite(ResourceKey.streamCodec(Registries.RECIPE), RecipeHolder::id,
                    Recipe.STREAM_CODEC.map(DesignerRecipe.class::cast, Recipe.class::cast), RecipeHolder::value,
                    RecipeHolder::new);
    private static final StreamCodec<RegistryFriendlyByteBuf, List<RecipeHolder<DesignerRecipe>>> LIST_STREAM_CODEC = ClientboundDesignerRecipesPayload.HOLDER_STREAM_CODEC
            .apply(ByteBufCodecs.list());
    public static final StreamCodec<ByteBuf, ClientboundDesignerRecipesPayload> STREAM_CODEC = StreamCodec.composite(
            ClientboundDesignerRecipesPayload.LIST_STREAM_CODEC.mapStream(RegistryFriendlyByteBuf.class::cast),
            ClientboundDesignerRecipesPayload::recipes, ClientboundDesignerRecipesPayload::new);
    
    public static final CustomPacketPayload.Type<ClientboundDesignerRecipesPayload> TYPE_PAYLOAD = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(NeoVillagersDesigner.MODID, "designer_recipe"));
    
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ClientboundDesignerRecipesPayload.TYPE_PAYLOAD;
    }
    
}
