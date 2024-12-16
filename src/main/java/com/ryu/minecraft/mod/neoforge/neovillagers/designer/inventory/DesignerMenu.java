package com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory;

import java.util.List;

import com.google.common.collect.Lists;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory.slots.ResultSingleSlot;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerRecipe;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupMenus;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupRecipeType;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

public class DesignerMenu extends AbstractContainerMenu {
    
    public static final String MENU_NAME = "designer";
    
    private static final int INV_SLOT_START = 2;
    private static final int USE_ROW_SLOT_END = 38;
    
    protected static SingleRecipeInput createRecipeInput(Container container) {
        return new SingleRecipeInput(container.getItem(0));
    }
    
    private final ContainerLevelAccess access;
    private final Slot inputSlot;
    private final Level level;
    private final Slot resultSlot;
    private final ResultContainer resultContainer = new ResultContainer();
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    
    private ItemStack input = ItemStack.EMPTY;
    private long lastSoundTime;
    private List<RecipeHolder<DesignerRecipe>> recipes = Lists.newArrayList();
    Runnable slotUpdateListener = () -> {
    };
    
    public final Container container = new SimpleContainer(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            DesignerMenu.this.slotsChanged(this);
            DesignerMenu.this.slotUpdateListener.run();
        }
    };
    
    public DesignerMenu(int pContainerId, Inventory playerInv) {
        this(pContainerId, playerInv, ContainerLevelAccess.NULL);
    }
    
    public DesignerMenu(int pContainerId, Inventory playerInv, ContainerLevelAccess pAccess) {
        super(SetupMenus.DESIGNER_CONTAINER.get(), pContainerId);
        this.access = pAccess;
        this.level = playerInv.player.level();
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 16, 33));
        this.resultSlot = this.addSlot(new ResultSingleSlot(this.resultContainer, 1, 140, 33, this));
        
        this.addInventorySlots(playerInv);
        this.addDataSlot(this.selectedRecipeIndex);
    }
    
    protected void addInventorySlots(Inventory inventory) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inventory, j + (i * 9) + 9, 8 + (j * 18), 84 + (i * 18)));
            }
        }
        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(inventory, k, 8 + (k * 18), 142));
        }
    }
    
    @Override
    public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
        return (pSlot.container != this.resultContainer) && super.canTakeItemForPickAll(pStack, pSlot);
    }
    
    @Override
    public boolean clickMenuButton(Player pPlayer, int pId) {
        if (this.isValidRecipeIndex(pId)) {
            this.selectedRecipeIndex.set(pId);
            this.setupResultSlot();
        }
        
        return true;
    }
    
    private ItemStack getItemStackFromIndex(int index, Level level) {
        final RecipeHolder<DesignerRecipe> recipeholder = this.recipes.get(index);
        return recipeholder.value().assemble(DesignerMenu.createRecipeInput(this.container), level.registryAccess());
    }
    
    public int getNumRecipes() {
        return this.recipes.size();
    }
    
    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
    }
    
    public boolean hasInputItem() {
        return this.inputSlot.hasItem() && !this.recipes.isEmpty();
    }
    
    private boolean isIngredient(Level pLevel, SingleRecipeInput pSingleRecipeInput) {
        return pLevel.getRecipeManager().getRecipeFor(SetupRecipeType.DESIGNER.get(), pSingleRecipeInput, pLevel)
                .isPresent();
    }
    
    private boolean isValidRecipeIndex(int pRecipeIndex) {
        return (pRecipeIndex >= 0) && (pRecipeIndex < this.recipes.size());
    }
    
    public void onTake(Player pPlayer, ItemStack pStack) {
        pStack.onCraftedBy(pPlayer.level(), pPlayer, pStack.getCount());
        DesignerMenu.this.resultContainer.awardUsedRecipes(pPlayer, List.of(this.inputSlot.getItem()));
        final ItemStack itemstack = DesignerMenu.this.inputSlot.remove(1);
        if (!itemstack.isEmpty()) {
            DesignerMenu.this.setupResultSlot();
        }
        
        this.access.execute((pLevel, pPos) -> {
            final long l = pLevel.getGameTime();
            if (DesignerMenu.this.lastSoundTime != l) {
                pLevel.playSound(null, pPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                DesignerMenu.this.lastSoundTime = l;
            }
        });
    }
    
    private boolean quickMoveInventory(int pIndex, ItemStack itemstack1) {
        if ((pIndex >= DesignerMenu.INV_SLOT_START) && (pIndex < 29)) {
            if (!this.moveItemStackTo(itemstack1, 29, DesignerMenu.USE_ROW_SLOT_END, false)) {
                return true;
            }
        } else if ((pIndex >= 29) && (pIndex < DesignerMenu.USE_ROW_SLOT_END)
                && !this.moveItemStackTo(itemstack1, DesignerMenu.INV_SLOT_START, 29, false)) {
            return true;
        }
        return false;
    }
    
    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = this.slots.get(pIndex);
        if (!slot.hasItem()) {
            return itemstack;
        }
        
        final ItemStack itemstack1 = slot.getItem();
        final Item item = itemstack1.getItem();
        itemstack = itemstack1.copy();
        if (pIndex == 1) {
            item.onCraftedBy(itemstack1, pPlayer.level(), pPlayer);
            if (!this.moveItemStackTo(itemstack1, DesignerMenu.INV_SLOT_START, DesignerMenu.USE_ROW_SLOT_END, true)) {
                return ItemStack.EMPTY;
            }
            
            slot.onQuickCraft(itemstack1, itemstack);
        } else if (pIndex == 0) {
            if (!this.moveItemStackTo(itemstack1, DesignerMenu.INV_SLOT_START, DesignerMenu.USE_ROW_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }
        } else if (this.isIngredient(this.level, new SingleRecipeInput(itemstack1))) {
            if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                return ItemStack.EMPTY;
            }
        } else if (this.quickMoveInventory(pIndex, itemstack1)) {
            return ItemStack.EMPTY;
        }
        
        if (itemstack1.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        }
        
        slot.setChanged();
        if (itemstack1.getCount() == itemstack.getCount()) {
            return ItemStack.EMPTY;
        }
        
        slot.onTake(pPlayer, itemstack1);
        this.broadcastChanges();
        
        return itemstack;
    }
    
    public void registerUpdateListener(Runnable pListener) {
        this.slotUpdateListener = pListener;
    }
    
    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.resultContainer.removeItemNoUpdate(1);
        this.access.execute((pLevel, pPos) -> this.clearContainer(pPlayer, this.container));
    }
    
    protected void setupResultSlot() {
        final int index = this.selectedRecipeIndex.get();
        if (!this.recipes.isEmpty() && this.isValidRecipeIndex(index)) {
            final ItemStack itemStack = this.getItemStackFromIndex(index, this.level);
            if (itemStack.isItemEnabled(this.level.enabledFeatures())) {
                this.resultContainer.setRecipeUsed(this.recipes.get(index));
                this.resultSlot.set(itemStack);
            } else {
                this.resultSlot.set(ItemStack.EMPTY);
            }
        } else {
            this.resultSlot.set(ItemStack.EMPTY);
        }
        
        this.broadcastChanges();
    }
    
    @Override
    public void slotsChanged(Container pInventory) {
        final ItemStack itemstack = this.inputSlot.getItem();
        if (!itemstack.is(this.input.getItem())) {
            this.input = itemstack.copy();
            this.recipes.clear();
            this.selectedRecipeIndex.set(-1);
            this.resultSlot.set(ItemStack.EMPTY);
            if (!itemstack.isEmpty()) {
                this.recipes = this.level.getRecipeManager().getRecipesFor(SetupRecipeType.DESIGNER.get(),
                        DesignerMenu.createRecipeInput(this.container), this.level);
            }
        }
    }
    
    @Override
    public boolean stillValid(Player pPlayer) {
        return AbstractContainerMenu.stillValid(this.access, pPlayer, SetupBlocks.DESIGNER.get());
    }
    
    public List<RecipeHolder<DesignerRecipe>> getRecipes() {
        return this.recipes;
    }
    
}
