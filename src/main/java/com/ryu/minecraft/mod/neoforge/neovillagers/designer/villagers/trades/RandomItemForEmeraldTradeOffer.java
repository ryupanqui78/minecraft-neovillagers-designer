package com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.trades;

import java.util.List;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

public class RandomItemForEmeraldTradeOffer implements VillagerTrades.ItemListing {
    
    private final int cost;
    private final int maxUses;
    private final int villagerXp;
    private final List<Item> listPossibleItems;
    
    public RandomItemForEmeraldTradeOffer(int pCost, int pMaxUses, int pVillagerXp, List<Item> pPossibleItems) {
        this.cost = pCost;
        this.maxUses = pMaxUses;
        this.villagerXp = pVillagerXp;
        this.listPossibleItems = pPossibleItems;
    }
    
    @Override
    public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
        final int i = pRandom.nextInt(this.listPossibleItems.size());
        final ItemStack itemStack = new ItemStack(this.listPossibleItems.get(i));
        final ItemStack itemCost = new ItemStack(Items.EMERALD, this.cost);
        return new MerchantOffer(itemCost, itemStack, this.maxUses, this.villagerXp, 0.05F);
    }
    
}
