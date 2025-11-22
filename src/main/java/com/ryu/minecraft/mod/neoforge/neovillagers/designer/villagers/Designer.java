package com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupVillagers;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.trades.EmeraldForItemByVillagerTypeTradeOffer;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.trades.ItemForEmeraldTradeOffer;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.trades.RandomItemForEmeraldTradeOffer;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class Designer extends Worker {
    
    public static final String ENTITY_NAME = "designer";
    public static final String ENTITY_POI_NAME = "designer_poi";
    
    private static final List<Item> RANDOM_ITEMS = List.of(Items.WHITE_BANNER, Items.BLACK_BANNER, Items.BLUE_BANNER,
            Items.BROWN_BANNER, Items.CYAN_BANNER, Items.GRAY_BANNER, Items.GREEN_BANNER, Items.LIGHT_BLUE_BANNER,
            Items.LIGHT_GRAY_BANNER, Items.LIME_BANNER, Items.MAGENTA_BANNER, Items.ORANGE_BANNER, Items.PINK_BANNER,
            Items.PURPLE_BANNER, Items.RED_BANNER, Items.YELLOW_BANNER);
    private static final Map<ResourceKey<VillagerType>, Item> VILLAGE_PLANK_ITEMS = ImmutableMap
            .<ResourceKey<VillagerType>, Item> builder().put(VillagerType.PLAINS, Items.OAK_PLANKS)
            .put(VillagerType.TAIGA, Items.SPRUCE_PLANKS).put(VillagerType.SNOW, Items.SPRUCE_PLANKS)
            .put(VillagerType.DESERT, Items.DEAD_BUSH).put(VillagerType.JUNGLE, Items.JUNGLE_PLANKS)
            .put(VillagerType.SAVANNA, Items.ACACIA_PLANKS).put(VillagerType.SWAMP, Items.MANGROVE_PLANKS).build();
    
    public static VillagerProfession registerVillager() {
        final ResourceLocation villagerResource = ResourceLocation.fromNamespaceAndPath(NeoVillagersDesigner.MODID,
                Designer.ENTITY_NAME);
        final Component villager = Component
                .translatable("entity." + villagerResource.getNamespace() + ".villager." + villagerResource.getPath());
        return new VillagerProfession(villager, x -> x.is(SetupVillagers.DESIGNER_POI.getKey()),
                x -> x.is(SetupVillagers.DESIGNER_POI.getKey()), ImmutableSet.of(), ImmutableSet.of(),
                SoundEvents.VILLAGER_CELEBRATE);
    }
    
    @Override
    protected ItemListing[] getLevel1() {
        final ItemListing option1 = new ItemForEmeraldTradeOffer(SetupBlocks.DECOR_BARREL_BLOCK.get().asItem(), 2, 3, 8,
                4);
        final ItemListing option2 = new ItemForEmeraldTradeOffer(SetupBlocks.DECOR_COMPOSTER_BLOCK.get().asItem(), 2, 3,
                8, 4);
        final ItemListing option3 = new EmeraldForItemByVillagerTypeTradeOffer(6, 8, 4, Designer.VILLAGE_PLANK_ITEMS);
        
        return new VillagerTrades.ItemListing[] { option1, option2, option3, };
    }
    
    @Override
    protected ItemListing[] getLevel2() {
        final ItemListing option1 = new ItemForEmeraldTradeOffer(Items.ITEM_FRAME, 5, 1, 12, 6);
        final ItemListing option2 = new ItemForEmeraldTradeOffer(Items.FLOWER_POT, 1, 3, 6, 4);
        final ItemListing option3 = new ItemForEmeraldTradeOffer(Items.BOOKSHELF, 8, 1, 12, 2);
        final ItemListing option4 = new ItemForEmeraldTradeOffer(SetupBlocks.DECOR_CHEST_BLOCK.get().asItem(), 1, 2, 6,
                4);
        
        return new VillagerTrades.ItemListing[] { option1, option2, option3, option4, };
    }
    
    @Override
    protected ItemListing[] getLevel3() {
        final ItemListing option1 = new ItemForEmeraldTradeOffer(SetupBlocks.DECOR_CAULDRON_BLOCK.get().asItem(), 1, 1,
                6, 4);
        final ItemListing option2 = new ItemForEmeraldTradeOffer(Items.IRON_CHAIN, 1, 3, 8, 4);
        final ItemListing option3 = new RandomItemForEmeraldTradeOffer(2, 6, 4, Designer.RANDOM_ITEMS);
        
        return new VillagerTrades.ItemListing[] { option1, option2, option3, };
    }
    
    @Override
    protected ItemListing[] getLevel4() {
        final ItemListing option1 = new ItemForEmeraldTradeOffer(SetupBlocks.DECOR_BOX_BLOCK.get().asItem(), 3, 2, 8,
                6);
        final ItemListing option2 = new ItemForEmeraldTradeOffer(SetupBlocks.DECOR_BOX_CROSS_BLOCK.get().asItem(), 3, 2,
                8, 6);
        final ItemListing option3 = new ItemForEmeraldTradeOffer(SetupBlocks.DECOR_BOX_FIX_BLOCK.get().asItem(), 3, 2,
                8, 6);
        
        return new VillagerTrades.ItemListing[] { option1, option2, option3, };
    }
    
    @Override
    protected ItemListing[] getLevel5() {
        final ItemListing option1 = new ItemForEmeraldTradeOffer(Items.PAINTING, 2, 3, 6, 4);
        final ItemListing option2 = new ItemForEmeraldTradeOffer(Items.ARMOR_STAND, 1, 2, 6, 4);
        final ItemListing option3 = new ItemForEmeraldTradeOffer(Items.DECORATED_POT, 2, 3, 6, 4);
        
        return new VillagerTrades.ItemListing[] { option1, option2, option3, };
    }
    
}
