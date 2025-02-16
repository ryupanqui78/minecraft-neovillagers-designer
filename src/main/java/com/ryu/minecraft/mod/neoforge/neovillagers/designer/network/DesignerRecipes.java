package com.ryu.minecraft.mod.neoforge.neovillagers.designer.network;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.client.ClientDesignerRecipes;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.server.ServerDesignerRecipes;

import net.minecraft.world.level.Level;

public class DesignerRecipes {
    
    public static DesignerRecipeInputs inputs(Level level) {
        return level.isClientSide ? ClientDesignerRecipes.inputs() : ServerDesignerRecipes.inputs();
    }
    
    private DesignerRecipes() {
    }
}
