package com.ryu.minecraft.mod.neoforge.neovillagers.designer.client;

import java.util.List;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory.DesignerMenu;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerRecipe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DesignerScreen extends AbstractContainerScreen<DesignerMenu> {
    
    private static final int RECIPES_COLUMNS = 4;
    private static final int RECIPES_IMAGE_SIZE = 18;
    private static final int RECIPES_ROWS = 3;
    private static final int RESULT_START_X = 41;
    private static final int RESULT_START_Y = 17;
    private static final int SCROLLER_CONTENT_SIZE = 54;
    private static final int SCROLLER_HEIGHT = 15;
    private static final int SCROLLER_START_X = 116;
    private static final int SCROLLER_START_Y = 17;
    private static final int SCROLLER_WIDTH = 12;
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NeoVillagersDesigner.MODID,
            "textures/gui/container/singlecontainer.png");
    
    private boolean scrolling;
    private float scrollOffs;
    private int startIndex;
    private boolean displayRecipes;
    
    public DesignerScreen(DesignerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        pMenu.registerUpdateListener(this::containerChanged);
    }
    
    private void containerChanged() {
        this.displayRecipes = this.menu.hasInputItem();
        if (!this.displayRecipes) {
            this.scrollOffs = 0.0F;
            this.startIndex = 0;
        }
    }
    
    protected int getOffscreenRows() {
        return (((this.menu.getNumRecipes() + DesignerScreen.RECIPES_COLUMNS) - 1) / DesignerScreen.RECIPES_COLUMNS)
                - 3;
    }
    
    private boolean isScrollBarActive() {
        return this.displayRecipes && (this.menu.getNumRecipes() > DesignerScreen.SCROLLER_WIDTH);
    }
    
    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        this.scrolling = false;
        if (this.displayRecipes) {
            final int indexLastVisibleElement = this.startIndex
                    + (DesignerScreen.RECIPES_COLUMNS * DesignerScreen.RECIPES_ROWS);
            final int initialIngredientPosX = this.leftPos + DesignerScreen.RESULT_START_X;
            final int initialIngredientPosY = this.topPos + DesignerScreen.RESULT_START_Y;
            final int initialScrollPosX = this.leftPos + DesignerScreen.SCROLLER_START_X;
            final int initialScrollPosY = this.topPos + DesignerScreen.SCROLLER_START_Y;
            final int maxScrollPosY = initialScrollPosY + DesignerScreen.SCROLLER_CONTENT_SIZE;
            
            for (int i = this.startIndex; i < indexLastVisibleElement; ++i) {
                final int indexInScreen = i - this.startIndex;
                final int carryX = indexInScreen % DesignerScreen.RECIPES_COLUMNS;
                final int carryY = indexInScreen / DesignerScreen.RECIPES_COLUMNS;
                final double d0 = pMouseX - (initialIngredientPosX + (carryX * DesignerScreen.RECIPES_IMAGE_SIZE));
                final double d1 = pMouseY - (initialIngredientPosY + (carryY * DesignerScreen.RECIPES_IMAGE_SIZE));
                if ((d0 >= 0.0) && (d1 >= 0.0) && (d0 < DesignerScreen.RECIPES_IMAGE_SIZE)
                        && (d1 < DesignerScreen.RECIPES_IMAGE_SIZE)
                        && this.menu.clickMenuButton(this.minecraft.player, i)) {
                    Minecraft.getInstance().getSoundManager()
                            .play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, i);
                    return true;
                }
            }
            
            if ((pMouseX >= initialScrollPosX) && (pMouseX < (initialScrollPosX + DesignerScreen.SCROLLER_WIDTH))
                    && (pMouseY >= initialScrollPosY) && (pMouseY < maxScrollPosY)) {
                this.scrolling = true;
            }
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }
    
    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (this.scrolling && this.isScrollBarActive()) {
            final int initialScrollPosY = this.topPos + DesignerScreen.SCROLLER_START_Y;
            final int maxScrollPosY = initialScrollPosY + DesignerScreen.SCROLLER_CONTENT_SIZE;
            this.scrollOffs = ((float) pMouseY - initialScrollPosY - 7.5f)
                    / (maxScrollPosY - initialScrollPosY - 15.0f);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0f);
            this.startIndex = (int) ((this.scrollOffs * this.getOffscreenRows()) + 0.5)
                    * DesignerScreen.RECIPES_COLUMNS;
            return true;
        } else {
            return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        }
    }
    
    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pScrollX, double pScrollY) {
        if (this.isScrollBarActive()) {
            final int i = this.getOffscreenRows();
            final float f = (float) pScrollY / i;
            this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
            this.startIndex = (int) ((this.scrollOffs * i) + 0.5) * DesignerScreen.RECIPES_COLUMNS;
        }
        
        return true;
    }
    
    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
    
    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blit(RenderType::guiTextured, DesignerScreen.TEXTURE, this.leftPos, this.topPos, 0, 0,
                this.imageWidth, this.imageHeight, 256, 256);
        final int k = (int) (39.0F * this.scrollOffs);
        final int posScrollImageX = this.isScrollBarActive() ? 176 : 188;
        final int initialScrollPosX = this.leftPos + DesignerScreen.SCROLLER_START_X;
        final int initialScrollPosY = this.topPos + DesignerScreen.SCROLLER_START_Y;
        
        pGuiGraphics.blit(RenderType::guiTextured, DesignerScreen.TEXTURE, initialScrollPosX, initialScrollPosY + k,
                posScrollImageX, 0, DesignerScreen.SCROLLER_WIDTH, DesignerScreen.SCROLLER_HEIGHT, 256, 256);
        this.renderButtons(pGuiGraphics, pMouseX, pMouseY);
    }
    
    private void renderButtons(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        final int initialPosX = this.leftPos + DesignerScreen.RESULT_START_X;
        final int initialPosY = this.topPos + DesignerScreen.RESULT_START_Y;
        final int indexLastVisibleElement = this.startIndex
                + (DesignerScreen.RECIPES_COLUMNS * DesignerScreen.RECIPES_ROWS);
        final List<RecipeHolder<DesignerRecipe>> list = this.menu.getVisibleRecipes();
        
        for (int i = this.startIndex; (i < indexLastVisibleElement) && (i < this.menu.getNumRecipes()); ++i) {
            final int indexInScreen = i - this.startIndex;
            final int posIngredientX = initialPosX
                    + ((indexInScreen % DesignerScreen.RECIPES_COLUMNS) * DesignerScreen.RECIPES_IMAGE_SIZE);
            final int row = indexInScreen / DesignerScreen.RECIPES_COLUMNS;
            final int posIngredientY = initialPosY + (row * DesignerScreen.RECIPES_IMAGE_SIZE);
            
            int posImageX = 0;
            if (i == this.menu.getSelectedRecipeIndex()) {
                posImageX = 36;
            } else if ((pMouseX >= posIngredientX) && (pMouseY >= posIngredientY)
                    && (pMouseX < (posIngredientX + DesignerScreen.RECIPES_IMAGE_SIZE))
                    && (pMouseY < (posIngredientY + DesignerScreen.RECIPES_IMAGE_SIZE))) {
                posImageX = 18;
            }
            pGuiGraphics.blit(RenderType::guiTextured, DesignerScreen.TEXTURE, posIngredientX, posIngredientY,
                    posImageX, 166, DesignerScreen.RECIPES_IMAGE_SIZE, DesignerScreen.RECIPES_IMAGE_SIZE, 256, 256);
            pGuiGraphics.renderItem(list.get(i).value().getResult(), posIngredientX + 1, posIngredientY + 1);
        }
    }
    
    @Override
    protected void renderTooltip(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        super.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
        if (this.displayRecipes) {
            final int initialPosX = this.leftPos + DesignerScreen.RESULT_START_X;
            final int initialPosY = this.topPos + DesignerScreen.RESULT_START_Y;
            final int indexLastVisibleElement = this.startIndex
                    + (DesignerScreen.RECIPES_COLUMNS * DesignerScreen.RECIPES_ROWS);
            final List<RecipeHolder<DesignerRecipe>> list = this.menu.getVisibleRecipes();
            
            for (int i = this.startIndex; (i < indexLastVisibleElement) && (i < this.menu.getNumRecipes()); ++i) {
                final int indexInScreen = i - this.startIndex;
                final int posIngredientX = initialPosX
                        + ((indexInScreen % DesignerScreen.RECIPES_COLUMNS) * DesignerScreen.RECIPES_IMAGE_SIZE);
                final int row = indexInScreen / DesignerScreen.RECIPES_COLUMNS;
                final int posIngredientY = initialPosY + (row * DesignerScreen.RECIPES_IMAGE_SIZE);
                if ((pMouseX >= posIngredientX) && (pMouseY >= posIngredientY)
                        && (pMouseX < (posIngredientX + DesignerScreen.RECIPES_IMAGE_SIZE))
                        && (pMouseY < (posIngredientY + DesignerScreen.RECIPES_IMAGE_SIZE))) {
                    pGuiGraphics.renderTooltip(this.font, list.get(i).value().getResult(), pMouseX, pMouseY);
                }
            }
        }
    }
    
}
