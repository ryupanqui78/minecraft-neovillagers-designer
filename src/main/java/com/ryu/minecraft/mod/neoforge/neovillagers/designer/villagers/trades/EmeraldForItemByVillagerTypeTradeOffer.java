package com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.trades;

import java.util.Map;
import java.util.Optional;

import org.jspecify.annotations.Nullable;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.VillagerDataHolder;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.entity.npc.villager.VillagerType;
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
    
    @Override
    public @Nullable MerchantOffer getOffer(ServerLevel level, Entity entity, RandomSource random) {
        if (entity instanceof final VillagerDataHolder villagerdataholder) {
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
