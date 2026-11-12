package com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.trades;

import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

public class ItemForEmeraldVillagerType implements VillagerTrades.ItemListing {
    
    private final Map<VillagerType, Item> trades;
    private final int cost;
    private final int count;
    private final int maxUses;
    private final int villagerXp;
    
    public ItemForEmeraldVillagerType(int emeraldCost, int count, int pMaxUses, int pVillagerXp, Map<VillagerType, Item> pTrades) {
        BuiltInRegistries.VILLAGER_TYPE.stream().filter(key -> !pTrades.containsKey(key)).findAny().ifPresent(ex -> {
            throw new IllegalStateException(
                    "Missing trade for villager type: " + BuiltInRegistries.VILLAGER_TYPE.getKey(ex));
        });
        this.trades = pTrades;
        this.count = count;
        this.cost = emeraldCost;
        this.maxUses = pMaxUses;
        this.villagerXp = pVillagerXp;
    }
    
    @Nullable
    @Override
    public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
        if (pTrader instanceof final VillagerDataHolder villagerdataholder) {
            final ItemStack itemstack = new ItemStack(this.trades.get(villagerdataholder.getVillagerData().getType()),
                    this.count);
            return new MerchantOffer(new ItemCost(Items.EMERALD, this.cost), itemstack, this.maxUses, this.villagerXp,
                    0.05F);
        } else {
            return null;
        }
    }
    
}
