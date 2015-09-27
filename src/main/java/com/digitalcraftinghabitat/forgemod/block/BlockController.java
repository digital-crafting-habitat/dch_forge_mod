package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.core.TabDigitalCraftingHabitat;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Created by Rauca on 25.08.2015.
 */
public class BlockController extends Block {
    public static Block blockController;
    private World world;


    protected BlockController(Material material) {
        super(material);
        setBlockName("blockController");
        setCreativeTab(TabDigitalCraftingHabitat.tab);
        setBlockTextureName(RefStrings.MODID + ":ore/dch_logo_block");
        setBlockUnbreakable();
    }

    public static void register() {
        GameRegistry.registerBlock(blockController = new BlockController(Material.circuits), blockController.getUnlocalizedName());
    }

    @Override
    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        this.world = p_149660_1_;
        ControllerRefreshThread.init();
        return super.onBlockPlaced(p_149660_1_, p_149660_2_, p_149660_3_, p_149660_4_, p_149660_5_, p_149660_6_, p_149660_7_, p_149660_8_, p_149660_9_);
    }

}


