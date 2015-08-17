package com.digitalcraftinghabitat.forgemod.item;

import com.digitalcraftinghabitat.forgemod.DigitalCraftingHabitatMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by christopher on 12/08/15.
 */
public class ItemCraftium extends Item {

    public ItemCraftium() {
        super();
        this.setCreativeTab(CreativeTabs.tabMaterials);
        setTextureName("digitalcraftinghabitat:craftium");
        setUnlocalizedName("craftium");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        if (!player.capabilities.isCreativeMode) {
            stack.stackSize--;

        }
        return stack;
    }
}
