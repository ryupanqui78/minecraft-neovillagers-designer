package com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.trades.EmeraldForItemVillagerType;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.trades.ItemForEmeraldTradeOffer;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.trades.RandomItemForEmeraldTradeOffer;

import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class Designer extends Worker {
    
    public static final String ENTITY_NAME = "designer";
    public static final String ENTITY_POI_NAME = "designer_poi";
    
    @Override
    protected ItemListing[] getLevel1() {
        final ItemListing option1 = new ItemForEmeraldTradeOffer(SetupBlocks.DECOR_BARREL_BLOCK.get().asItem(), 2, 3, 8,
                4);
        final ItemListing option2 = new ItemForEmeraldTradeOffer(SetupBlocks.DECOR_COMPOSTER_BLOCK.get().asItem(), 2, 3,
                8, 4);
        final ItemListing option3 = new EmeraldForItemVillagerType(6, 8, 4,
                ImmutableMap.<VillagerType, Item> builder().put(VillagerType.PLAINS, Items.OAK_PLANKS)
                        .put(VillagerType.TAIGA, Items.SPRUCE_PLANKS).put(VillagerType.SNOW, Items.SPRUCE_PLANKS)
                        .put(VillagerType.DESERT, Items.DEAD_BUSH).put(VillagerType.JUNGLE, Items.JUNGLE_PLANKS)
                        .put(VillagerType.SAVANNA, Items.ACACIA_PLANKS).put(VillagerType.SWAMP, Items.MANGROVE_PLANKS)
                        .build());
        
        return new VillagerTrades.ItemListing[] { option1, option2, option3, };
    }
    
    @Override
    protected ItemListing[] getLevel2() {
        final ItemListing option1 = new ItemForEmeraldTradeOffer(Items.ITEM_FRAME, 5, 1, 12, 6);
        final ItemListing option2 = new ItemForEmeraldTradeOffer(Items.FLOWER_POT, 1, 3, 6, 4);
        final ItemListing option3 = new ItemForEmeraldTradeOffer(Items.BOOKSHELF, 8, 1, 12, 2);
        
        return new VillagerTrades.ItemListing[] { option1, option2, option3, };
    }
    
    @Override
    protected ItemListing[] getLevel3() {
        final List<Item> listItems = new ArrayList<>();
        listItems.add(Items.WHITE_BANNER);
        listItems.add(Items.BLACK_BANNER);
        listItems.add(Items.BLUE_BANNER);
        listItems.add(Items.BROWN_BANNER);
        listItems.add(Items.CYAN_BANNER);
        listItems.add(Items.GRAY_BANNER);
        listItems.add(Items.GREEN_BANNER);
        listItems.add(Items.LIGHT_BLUE_BANNER);
        listItems.add(Items.LIGHT_GRAY_BANNER);
        listItems.add(Items.LIME_BANNER);
        listItems.add(Items.MAGENTA_BANNER);
        listItems.add(Items.ORANGE_BANNER);
        listItems.add(Items.PINK_BANNER);
        listItems.add(Items.PURPLE_BANNER);
        listItems.add(Items.RED_BANNER);
        listItems.add(Items.YELLOW_BANNER);
        final ItemListing option1 = new ItemForEmeraldTradeOffer(SetupBlocks.DECOR_CAULDRON_BLOCK.get().asItem(), 1, 1,
                6, 4);
        final ItemListing option2 = new ItemForEmeraldTradeOffer(SetupBlocks.DECOR_CHEST_BLOCK.get().asItem(), 1, 2, 6,
                4);
        final ItemListing option3 = new RandomItemForEmeraldTradeOffer(2, 6, 4, listItems);
        
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
        
        return new VillagerTrades.ItemListing[] { option1, option2, };
    }
    
}
