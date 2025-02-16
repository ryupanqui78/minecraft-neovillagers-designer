package com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerRecipe;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.DesignerRecipeInputs;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.client.ClientboundDesignerRecipesPayload;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupRecipeType;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class ServerDesignerRecipeInputs implements DesignerRecipeInputs {
    
    private final RecipeManager recipeManager;
    
    private List<RecipeHolder<DesignerRecipe>> recipes = new ArrayList<>();
    
    public ServerDesignerRecipeInputs(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;
    }
    
    public void loadRecipes() {
        final MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) { // Should never be null
            this.recipes = new ArrayList<>();
            this.recipeManager.recipeMap().byType(SetupRecipeType.DESIGNER.get())
                    .forEach(element -> this.recipes.add(element));
        }
    }
    
    @Override
    public List<RecipeHolder<DesignerRecipe>> recipes() {
        return this.recipes;
    }
    
    public void syncToClient(Stream<ServerPlayer> players) {
        final ClientboundDesignerRecipesPayload payload = new ClientboundDesignerRecipesPayload(this.recipes);
        players.forEach(player -> PacketDistributor.sendToPlayer(player, payload));
    }
}
