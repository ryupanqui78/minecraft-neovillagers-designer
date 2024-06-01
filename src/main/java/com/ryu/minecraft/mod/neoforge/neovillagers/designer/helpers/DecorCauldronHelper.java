package com.ryu.minecraft.mod.neoforge.neovillagers.designer.helpers;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup.SetupBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class DecorCauldronHelper {
    
    public static void fillBucket(Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, ItemStack itemStack) {
        if (!pLevel.isClientSide) {
            final Item item = itemStack.getItem();
            final ItemStack itemResult = new ItemStack(Items.WATER_BUCKET);
            pPlayer.setItemInHand(pHand, ItemUtils.createFilledResult(itemStack, pPlayer, itemResult));
            pPlayer.awardStat(Stats.USE_CAULDRON);
            pPlayer.awardStat(Stats.ITEM_USED.get(item));
            pLevel.setBlockAndUpdate(pPos, SetupBlocks.DECOR_CAULDRON_BLOCK.get().defaultBlockState());
            pLevel.playSound((Player) null, pPos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.gameEvent((Entity) null, GameEvent.FLUID_PICKUP, pPos);
        }
    }
    
    public static void fillCauldron(Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, ItemStack itemStack) {
        if (!pLevel.isClientSide) {
            final Item item = itemStack.getItem();
            pPlayer.setItemInHand(pHand, ItemUtils.createFilledResult(itemStack, pPlayer, new ItemStack(Items.BUCKET)));
            pPlayer.awardStat(Stats.FILL_CAULDRON);
            pPlayer.awardStat(Stats.ITEM_USED.get(item));
            pLevel.setBlockAndUpdate(pPos, SetupBlocks.DECOR_WATER_CAULDRON_BLOCK.get().defaultBlockState());
            pLevel.playSound((Player) null, pPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.gameEvent((Entity) null, GameEvent.FLUID_PLACE, pPos);
        }
    }
    
    private DecorCauldronHelper() {
        
    }
}
