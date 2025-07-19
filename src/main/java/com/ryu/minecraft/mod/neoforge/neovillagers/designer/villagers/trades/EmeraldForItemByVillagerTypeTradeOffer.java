package com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.trades;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Nullable;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
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

public class EmeraldForItemByVillagerTypeTradeOffer implements VillagerTrades.ItemListing {
    
    private final Map<ResourceKey<VillagerType>, Item> trades;
    private final int cost;
    private final int maxUses;
    private final int villagerXp;
    
    public EmeraldForItemByVillagerTypeTradeOffer(int pCost, int pMaxUses, int pVillagerXp, Map<ResourceKey<VillagerType>, Item> pTrades) {
        BuiltInRegistries.VILLAGER_TYPE.registryKeySet().stream().filter(key -> !pTrades.containsKey(key)).findAny()
                .ifPresent(ex -> {
                    throw new IllegalStateException("Missing trade for villager type: " + ex);
                });
        this.trades = pTrades;
        this.cost = pCost;
        this.maxUses = pMaxUses;
        this.villagerXp = pVillagerXp;
    }
    
    @Nullable
    @Override
    public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
        if (pTrader instanceof final VillagerDataHolder villagerdataholder) {
            final Optional<ResourceKey<VillagerType>> resourcekey = villagerdataholder.getVillagerData().type()
                    .unwrapKey();
            if (resourcekey.isPresent()) {
                final ItemCost itemstack = new ItemCost(this.trades.get(resourcekey.get()), this.cost);
                return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, 0.05f);
            }
        }
        return null;
    }
    
}
