package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.DigitalCraftingHabitatMod;
import com.digitalcraftinghabitat.forgemod.item.DustCraftium;
import com.digitalcraftinghabitat.forgemod.item.ItemCraftiumCoal;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by Rauca on 25.08.2015.
 */
public class BlockController extends Block {
    public static Block blockController;

    protected BlockController(Material material) {
        super(material);
    }

    public static void mainRegistry() {
        initItem();
        registerItem();
        addRecipes();
    }

    public static void initItem() {
        blockController = new BlockController(Material.circuits).setBlockName("blockController").setCreativeTab(CreativeTabs.tabBlock);
        blockController.setBlockTextureName(DigitalCraftingHabitatMod.MODID + ":ore/dch_logo_block");
    }

    public static void registerItem() {
        GameRegistry.registerBlock(blockController, blockController.getUnlocalizedName());
    }

    public static void addRecipes() {
        GameRegistry.addRecipe(new ItemStack(ItemCraftiumCoal.coalCraftium), new Object[]{
                " D ",
                "DCD",
                " D ",
                'D', new ItemStack(DustCraftium.dustCraftium), 'C', Items.coal
        });
    }
}


