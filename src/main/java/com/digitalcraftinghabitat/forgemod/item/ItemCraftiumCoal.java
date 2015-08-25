package com.digitalcraftinghabitat.forgemod.item;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by christopher on 12/08/15.
 */
public class ItemCraftiumCoal extends Item {
    public static Item craftiumCoal;

    public ItemCraftiumCoal() {
        setUnlocalizedName("craftiumCoal");
        setCreativeTab(CreativeTabs.tabMisc);
        setTextureName(RefStrings.MODID + ":craftiumCoal");
    }

    public static void register() {
        GameRegistry.registerItem(craftiumCoal = new ItemCraftiumCoal(), craftiumCoal.getUnlocalizedName());
    }

    public static void addRecipes() {
        GameRegistry.addRecipe(new ItemStack(ItemCraftiumCoal.craftiumCoal), new Object[]{
                " D ",
                "DCD",
                " D ",
                'D', new ItemStack(DustCraftium.dustCraftium), 'C', Items.coal
        });
    }
/*
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        if (!player.capabilities.isCreativeMode) {
            stack.stackSize--;

        }
        return stack;
    } */
}
