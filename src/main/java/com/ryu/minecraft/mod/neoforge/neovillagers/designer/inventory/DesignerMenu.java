package com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory.slots.ResultSingleSlot;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.item.crafting.DesignerRecipe;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.network.DesignerRecipes;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupMenus;

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
    
    private static final int INPUT_SLOT = 0;
    private static final int RESULT_SLOT = 1;
    private static final int INV_SLOT_START = 2;
    private static final int INV_SLOT_END = 29;
    private static final int USE_ROW_SLOT_END = 38;
    
    private final ContainerLevelAccess access;
    private final Slot inputSlot;
    private final Level level;
    private final ResultContainer resultContainer = new ResultContainer();
    private final Slot resultSlot;
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    private final Container container = new SimpleContainer(1) {
        {
            Objects.requireNonNull(DesignerMenu.this);
        }
        
        @Override
        public void setChanged() {
            super.setChanged();
            DesignerMenu.this.slotsChanged(this);
            DesignerMenu.this.slotUpdateListener.run();
        }
    };
    
    private ItemStack input = ItemStack.EMPTY;
    private long lastSoundTime;
    
    private List<RecipeHolder<DesignerRecipe>> recipesForInput = new ArrayList<>();
    
    Runnable slotUpdateListener = () -> {
    };
    
    public DesignerMenu(int pContainerId, Inventory playerInv) {
        this(pContainerId, playerInv, ContainerLevelAccess.NULL);
    }
    
    public DesignerMenu(int pContainerId, Inventory playerInv, ContainerLevelAccess pAccess) {
        super(SetupMenus.DESIGNER.get(), pContainerId);
        this.access = pAccess;
        this.level = playerInv.player.level();
        this.inputSlot = this.addSlot(new Slot(this.container, DesignerMenu.INPUT_SLOT, 16, 33));
        this.resultSlot = this
                .addSlot(new ResultSingleSlot(this.resultContainer, DesignerMenu.RESULT_SLOT, 140, 33, this));
        
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
    public boolean canTakeItemForPickAll(ItemStack carried, Slot target) {
        return (target.container != this.resultContainer) && super.canTakeItemForPickAll(carried, target);
    }
    
    @Override
    public boolean clickMenuButton(Player player, int buttonId) {
        if (this.selectedRecipeIndex.get() == buttonId) {
            return false;
        }
        
        if (this.isValidRecipeIndex(buttonId)) {
            this.selectedRecipeIndex.set(buttonId);
            this.setupResultSlot(buttonId);
        }
        return true;
    }
    
    public void executeAccess() {
        this.access.execute((_, pos) -> {
            final long gameTime = this.level.getGameTime();
            if (this.lastSoundTime != gameTime) {
                this.level.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                this.lastSoundTime = gameTime;
            }
        });
    }
    
    public ContainerLevelAccess getAccess() {
        return this.access;
    }
    
    public Slot getInputSlot() {
        return this.inputSlot;
    }
    
    public int getNumberOfVisibleRecipes() {
        return this.recipesForInput.size();
    }
    
    public ResultContainer getResultContainer() {
        return this.resultContainer;
    }
    
    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
    }
    
    public List<RecipeHolder<DesignerRecipe>> getVisibleRecipes() {
        return this.recipesForInput;
    }
    
    public boolean hasInputItem() {
        return this.inputSlot.hasItem() && !this.recipesForInput.isEmpty();
    }
    
    private boolean isValidRecipeIndex(int buttonId) {
        return (buttonId >= 0) && (buttonId < this.recipesForInput.size());
    }
    
    private boolean quickMoveInventory(int slotIndex, ItemStack stack) {
        if ((slotIndex >= DesignerMenu.INV_SLOT_START) && (slotIndex < DesignerMenu.INV_SLOT_END)) {
            if (!this.moveItemStackTo(stack, 29, DesignerMenu.USE_ROW_SLOT_END, false)) {
                return true;
            }
        } else if ((slotIndex >= DesignerMenu.INV_SLOT_END) && (slotIndex < DesignerMenu.USE_ROW_SLOT_END)
                && !this.moveItemStackTo(stack, DesignerMenu.INV_SLOT_START, DesignerMenu.INV_SLOT_END, false)) {
            return true;
        }
        return false;
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        ItemStack clicked = ItemStack.EMPTY;
        final Slot slot = this.slots.get(slotIndex);
        if (!slot.hasItem()) {
            return clicked;
        }
        
        final ItemStack stack = slot.getItem();
        final Item item = stack.getItem();
        clicked = stack.copy();
        if (slotIndex == DesignerMenu.RESULT_SLOT) {
            item.onCraftedBy(stack, player);
            if (!this.moveItemStackTo(stack, DesignerMenu.INV_SLOT_START, DesignerMenu.USE_ROW_SLOT_END, true)) {
                return ItemStack.EMPTY;
            }
            
            slot.onQuickCraft(stack, clicked);
        } else if (slotIndex == DesignerMenu.INPUT_SLOT) {
            if (!this.moveItemStackTo(stack, DesignerMenu.INV_SLOT_START, DesignerMenu.USE_ROW_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }
        } else if (DesignerRecipes.inputs(this.level).test(stack)
                && !this.moveItemStackTo(stack, DesignerMenu.INPUT_SLOT, DesignerMenu.RESULT_SLOT, false)) {
            return ItemStack.EMPTY;
        } else if (this.quickMoveInventory(slotIndex, stack)) {
            return ItemStack.EMPTY;
        }
        
        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        }
        
        slot.setChanged();
        if (stack.getCount() == clicked.getCount()) {
            return ItemStack.EMPTY;
        }
        
        slot.onTake(player, stack);
        if (slotIndex == DesignerMenu.RESULT_SLOT) {
            player.drop(stack, false);
        }
        this.broadcastChanges();
        
        return clicked;
    }
    
    public void registerUpdateListener(Runnable pListener) {
        this.slotUpdateListener = pListener;
    }
    
    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.resultContainer.removeItemNoUpdate(1);
        this.access.execute((_, _) -> this.clearContainer(pPlayer, this.container));
    }
    
    private void setupRecipeList(ItemStack item) {
        this.selectedRecipeIndex.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (!item.isEmpty()) {
            this.recipesForInput = DesignerRecipes.inputs(this.level).recipes().stream()
                    .filter(holder -> holder.value().input().test(item)).toList();
        } else {
            this.recipesForInput = new ArrayList<>();
        }
    }
    
    public void setupResultSlot(int index) {
        Optional<RecipeHolder<DesignerRecipe>> usedRecipe;
        if (!this.recipesForInput.isEmpty() && this.isValidRecipeIndex(index)) {
            usedRecipe = Optional.ofNullable(this.recipesForInput.get(index));
        } else {
            usedRecipe = Optional.empty();
        }
        
        usedRecipe.ifPresentOrElse(recipe -> {
            this.resultContainer.setRecipeUsed(recipe);
            this.resultSlot.set(recipe.value().assemble(new SingleRecipeInput(this.container.getItem(0))));
        }, () -> {
            this.resultSlot.set(ItemStack.EMPTY);
            this.resultContainer.setRecipeUsed(null);
        });
        this.broadcastChanges();
    }
    
    @Override
    public void slotsChanged(Container container) {
        final ItemStack inputTmp = this.inputSlot.getItem();
        if (!inputTmp.is(this.input.getItem())) {
            this.input = inputTmp.copy();
            this.setupRecipeList(inputTmp);
        }
    }
    
    @Override
    public boolean stillValid(Player player) {
        return AbstractContainerMenu.stillValid(this.access, player, SetupBlocks.DESIGNER.get());
    }
}
