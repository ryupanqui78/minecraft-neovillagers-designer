package com.ryu.minecraft.mod.neoforge.neovillagers.designer.setup;

import com.ryu.minecraft.mod.neoforge.neovillagers.designer.NeoVillagersDesigner;
import com.ryu.minecraft.mod.neoforge.neovillagers.designer.inventory.DesignerMenu;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SetupMenus {
    
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU,
            NeoVillagersDesigner.MODID);
    
    public static final DeferredHolder<MenuType<?>, MenuType<DesignerMenu>> DESIGNER_CONTAINER = SetupMenus.MENUS
            .register(DesignerMenu.MENU_NAME,
                    () -> new MenuType<DesignerMenu>(DesignerMenu::new, FeatureFlags.DEFAULT_FLAGS));
    
    private SetupMenus() {
        
    }
}
