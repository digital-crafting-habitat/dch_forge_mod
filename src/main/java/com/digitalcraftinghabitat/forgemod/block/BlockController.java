package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.DigitalCraftingHabitatMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Rauca on 25.08.2015.
 */
public class BlockController extends Block{
    public static Block blockController;

    protected BlockController(Material material) {
        super(material);
    }

    public static void mainRegistry() {
        initItem();
        registerItem();
    }

    public static void  initItem() {
        blockController = new BlockController(Material.circuits).setBlockName("blockController").setCreativeTab(CreativeTabs.tabBlock);
        blockController.setBlockTextureName(DigitalCraftingHabitatMod.MODID + ":ore/dch_logo_block");
    }

    public static void registerItem() {
        GameRegistry.registerBlock(blockController, blockController.getUnlocalizedName());
    }
}


