package com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory.slots;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory.DesignerMenu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ResultSingleSlot extends Slot {
    
    private final DesignerMenu menu;
    
    public ResultSingleSlot(Container pContainer, int pSlot, int pX, int pY, DesignerMenu pMenu) {
        super(pContainer, pSlot, pX, pY);
        this.menu = pMenu;
    }
    
    @Override
    public boolean mayPlace(ItemStack pStack) {
        return false;
    }
    
    @Override
    public void onTake(Player pPlayer, ItemStack pStack) {
        this.menu.onTake(pPlayer, pStack);
        super.onTake(pPlayer, pStack);
    }
}
