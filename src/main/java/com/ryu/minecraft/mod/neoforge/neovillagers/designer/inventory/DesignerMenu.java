package com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupMenus;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;

public class DesignerMenu extends AbstractContainerMenu {
    
    public static final String MENU_NAME = "designer";
    
    private final ContainerLevelAccess access;
    
    public DesignerMenu(int pContainerId, Inventory playerInv) {
        this(pContainerId, playerInv, ContainerLevelAccess.NULL);
    }
    
    public DesignerMenu(int pContainerId, Inventory playerInv, ContainerLevelAccess pAccess) {
        super(SetupMenus.DESIGNER_CONTAINER.get(), pContainerId);
        this.access = pAccess;
    }
    
    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public boolean stillValid(Player pPlayer) {
        return AbstractContainerMenu.stillValid(this.access, pPlayer, SetupBlocks.DESIGNER_TABLE_BLOCK.get());
    }
    
}
