package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.Designer;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.villagers.Worker;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

@EventBusSubscriber(modid = NeoVillagersDesigner.MODID)
public class SetupGeneralEvents {
    
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == SetupVillagers.DESIGNER) {
            final Worker worker = new Designer();
            worker.getTrades(event);
        }
    }
    
    private SetupGeneralEvents() {
    }
}