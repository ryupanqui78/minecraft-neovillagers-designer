package com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.server;

import java.util.List;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.DesignerRecipeInputs;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;

public class ServerDesignerRecipes {
    
    private static ServerDesignerRecipeInputs inputs;
    
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof final ServerPlayer serverPlayer
                && serverPlayer.level() instanceof final ServerLevel serveLevel) {
            ServerDesignerRecipes.inputs = new ServerDesignerRecipeInputs(serveLevel.recipeAccess());
            ServerDesignerRecipes.inputs.loadRecipes();
            ServerDesignerRecipes.inputs.syncToClient(List.of(serverPlayer).stream());
        }
        
    }
    
    public static DesignerRecipeInputs inputs() {
        return ServerDesignerRecipes.inputs;
    }
    
    private ServerDesignerRecipes() {
    }
}
