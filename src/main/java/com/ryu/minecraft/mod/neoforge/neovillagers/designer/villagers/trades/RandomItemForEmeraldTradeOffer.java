package com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.trades;

import java.util.List;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

public class RandomItemForEmeraldTradeOffer implements VillagerTrades.ItemListing {
    
    private final ItemCost itemCost;
    private final List<Item> listPossibleItems;
    private final int maxUses;
    private final int villagerXp;
    
    public RandomItemForEmeraldTradeOffer(int pCost, int pMaxUses, int pVillagerXp, List<Item> pPossibleItems) {
        this.maxUses = pMaxUses;
        this.villagerXp = pVillagerXp;
        this.itemCost = new ItemCost(Items.EMERALD, pCost);
        this.listPossibleItems = pPossibleItems;
    }
    
    @Override
    public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
        final int i = pRandom.nextInt(this.listPossibleItems.size());
        final ItemStack itemStack = new ItemStack(this.listPossibleItems.get(i));
        return new MerchantOffer(this.itemCost, itemStack, this.maxUses, this.villagerXp, 0.5f);
    }
    
}
