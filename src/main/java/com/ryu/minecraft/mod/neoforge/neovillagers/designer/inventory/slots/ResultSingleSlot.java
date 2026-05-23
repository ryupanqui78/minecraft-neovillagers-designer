package com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory.slots;

import java.util.List;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory.DesignerMenu;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ResultSingleSlot extends Slot {
    
    private final DesignerMenu menu;
    
    public ResultSingleSlot(Container container, int slot, int x, int y, DesignerMenu menu) {
        super(container, slot, x, y);
        this.menu = menu;
    }
    
    private List<ItemStack> getRelevantItems() {
        return List.of(this.menu.getInputSlot().getItem());
    }
    
    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return false;
    }
    
    @Override
    public void onTake(Player player, ItemStack carried) {
        carried.onCraftedBy(player, carried.getCount());
        this.menu.getResultContainer().awardUsedRecipes(player, this.getRelevantItems());
        final ItemStack remaining = this.menu.getInputSlot().remove(1);
        if (!remaining.isEmpty()) {
            this.menu.setupResultSlot(this.menu.getSelectedRecipeIndex());
        }
        
        this.menu.executeAccess();
        super.onTake(player, carried);
    }
}
