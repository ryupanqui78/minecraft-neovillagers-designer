package com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.client;

import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientDesignerRecipes {
    
    private static ClientDesignerRecipeInputs inputs;
    
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
