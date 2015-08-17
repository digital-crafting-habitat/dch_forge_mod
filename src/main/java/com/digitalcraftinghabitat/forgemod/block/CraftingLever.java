package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import net.minecraft.block.BlockLever;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

/**
 * Created by christopher on 18/08/15.
 */
public class CraftingLever extends BlockLever{

    public CraftingLever() {
        super();
        setBlockName("crafting_lever_block");
        setCreativeTab(CreativeTabs.tabRedstone);
        this.setResistance(3.0F);
        this.setHardness(5.0F);
    }

    @Override
    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        int superReturnValue =  super.onBlockPlaced(p_149660_1_, p_149660_2_, p_149660_3_, p_149660_4_, p_149660_5_, p_149660_6_, p_149660_7_, p_149660_8_, p_149660_9_);
        DCHLog.info("CraftingLever: it works");
        return superReturnValue;
    }
}
