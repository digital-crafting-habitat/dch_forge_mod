package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by christopher on 25/08/15.
 */
public class CraftingRedStoneConnector extends BlockRedstoneWire {

    boolean test = true;

    public CraftingRedStoneConnector() {
        super();
        setBlockName("crafting_redstone");
        setCreativeTab(CreativeTabs.tabRedstone);
    }

    @Override
    public boolean canProvidePower() {
        return test;
    }

    @Override
    public boolean isNormalCube() {
        return super.isNormalCube();
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_) {
        if (test) {
            return 15;
        }
        return 0;

    }

    @Override
    public boolean onBlockActivated(World world, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.isRemote) {
            return true;
        }
        DatahubClientConnector datahubClientConnector = new DatahubClientConnector();
        datahubClientConnector.getIntValueForKey("testkey");

        world.notifyBlockChange(p_149727_2_, p_149727_3_, p_149727_4_, this);
        return test;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
        if (test) {
            return 15;

        }
        return 0;
    }

    //GameRegistry.registerBlock(new CraftingRedStoneConnector(), "crafting_redstone");

}
