package com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.client;

import java.util.List;

import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientDesignerRecipes {
    
    private static ClientDesignerRecipeInputs inputs = new ClientDesignerRecipeInputs(List.of());
    
    // Handling the sent packet
    public static void handle(final ClientboundDesignerRecipesPayload data, final IPayloadContext context) {
        ClientDesignerRecipes.inputs = new ClientDesignerRecipeInputs(data.recipes());
    }
    
    public static ClientDesignerRecipeInputs inputs() {
        return ClientDesignerRecipes.inputs;
    }
    
    private ClientDesignerRecipes() {
    }
    
}
