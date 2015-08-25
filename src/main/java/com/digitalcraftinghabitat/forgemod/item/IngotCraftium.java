package com.digitalcraftinghabitat.forgemod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by christopher on 12/08/15.
 */
public class IngotCraftium extends Item {

    public IngotCraftium() {
        super();
        setCreativeTab(CreativeTabs.tabMaterials);
        setTextureName("digitalcraftinghabitat:craftium");
        setUnlocalizedName("craftium_ingot");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        if (!player.capabilities.isCreativeMode) {
            stack.stackSize--;
        }
        return stack;
    }


}
