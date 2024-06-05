package com.ryu.minecraft.mod.neoforge.neovillagers.designer.client;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory.DesignerMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DesignerScreen extends AbstractContainerScreen<DesignerMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(NeoVillagersDesigner.MODID,
            "textures/gui/container/designer.png");
    
    public DesignerScreen(DesignerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }
    
    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
    
    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        final int i = this.leftPos;
        final int j = this.topPos;
        pGuiGraphics.blit(DesignerScreen.TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
