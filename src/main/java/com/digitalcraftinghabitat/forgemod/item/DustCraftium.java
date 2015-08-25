package com.digitalcraftinghabitat.forgemod.item;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Rauca on 25.08.2015
 */
public class DustCraftium extends Item {
    public static Item dustCraftium;

    public DustCraftium() {
        setUnlocalizedName("dustCraftium");
        setCreativeTab(CreativeTabs.tabMisc);
        setTextureName(RefStrings.MODID + ":craftium_dust");
    }

    public static void register() {
        GameRegistry.registerItem(dustCraftium = new DustCraftium(), dustCraftium.getUnlocalizedName());
    }
}
