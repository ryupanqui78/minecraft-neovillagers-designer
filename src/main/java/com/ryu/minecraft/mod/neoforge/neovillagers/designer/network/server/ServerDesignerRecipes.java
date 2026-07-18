package com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.server;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.DesignerRecipeInputs;

import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

public class ServerDesignerRecipes {
    
    private static ServerDesignerRecipeInputs inputs = new ServerDesignerRecipeInputs(null);
    
    public static DesignerRecipeInputs inputs() {
        return ServerDesignerRecipes.inputs;
    }
    
    @SubscribeEvent
    public static void onDatapackSync(OnDatapackSyncEvent event) {
        final MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            ServerDesignerRecipes.inputs = new ServerDesignerRecipeInputs(server.getRecipeManager());
            ServerDesignerRecipes.inputs.loadRecipes();
            ServerDesignerRecipes.inputs.syncToClient(event.getRelevantPlayers());
        }
    }
    
    private ServerDesignerRecipes() {
    }
}
