package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by Rauca on 25.08.2015.
 */
public class BlockController extends Block {
    public static Block blockController;

    protected BlockController(Material material) {
        super(material);
        setBlockName("blockController");
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockTextureName(RefStrings.MODID + ":ore/dch_logo_block");
    }

    public static void register() {
        GameRegistry.registerBlock(blockController = new BlockController(Material.circuits), blockController.getUnlocalizedName());
    }

}


