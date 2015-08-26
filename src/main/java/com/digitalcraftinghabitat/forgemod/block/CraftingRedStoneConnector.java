package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import com.digitalcraftinghabitat.forgemod.tileentity.RedisValueEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressedPowered;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by christopher on 25/08/15.
 */
public class CraftingRedStoneConnector extends BlockCompressedPowered implements ITileEntityProvider {

    DatahubClientConnector datahubClientConnector = new DatahubClientConnector();
    public static boolean isActive = true;
    public static Block craftingRedStoneConnector;

    public CraftingRedStoneConnector() {
        super(MapColor.tntColor);
        setBlockTextureName(RefStrings.MODID + ":ore/Craftstone");
        setBlockName("crafting_redstone");
        setCreativeTab(CreativeTabs.tabRedstone);
        RedisValueEntity.init();
    }

    @Override
    public boolean canProvidePower() {
        return isActive;
    }

    @Override
    public boolean isNormalCube() {
        return super.isNormalCube();
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_) {
        if (isActive) {
            return 15;
        }
        return 0;

    }

    public static void register() {
        GameRegistry.registerBlock(craftingRedStoneConnector = new CraftingRedStoneConnector(), craftingRedStoneConnector.getUnlocalizedName());
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
        if (isActive) {
            return 15;

        }
        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new RedisValueEntity();
    }
}
