package com.digitalcraftinghabitat.forgemod.item;

import com.digitalcraftinghabitat.forgemod.DigitalCraftingHabitatMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Rauca on 25.08.2015
 */
public class DustCraftium {
    public static Item dustCraftium;

    public static void mainRegistry() {
        initItem();
        registerItem();
    }

    public static void  initItem() {
        dustCraftium = new Item().setUnlocalizedName("dustCraftium").setCreativeTab(CreativeTabs.tabMisc).setTextureName(DigitalCraftingHabitatMod.MODID + ":craftium_dust");
    }

    public static void registerItem() {
        GameRegistry.registerItem(dustCraftium, dustCraftium.getUnlocalizedName());
    }
}
