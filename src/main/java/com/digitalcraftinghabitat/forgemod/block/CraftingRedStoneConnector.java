package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.tileentity.RedisValueEntity;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import com.digitalcraftinghabitat.forgemod.util.DCHUtils;
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

import java.util.Random;

/**
 * Created by christopher on 25/08/15.
 */
public class CraftingRedStoneConnector extends BlockCompressedPowered implements ITileEntityProvider {

    public static Block craftingRedStoneConnector;
    private int count;

    public CraftingRedStoneConnector() {
        super(MapColor.tntColor);
        setBlockTextureName(RefStrings.MODID + ":ore/Craftstone");
        setBlockName("crafting_redstone");
        setCreativeTab(CreativeTabs.tabRedstone);
        RedisValueEntity.init();
    }

    @Override
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (p_149727_1_.isRemote)
            return false;
        RedisValueEntity entity = (RedisValueEntity) p_149727_1_.getTileEntity(p_149727_2_, p_149727_3_, p_149727_4_);
        p_149727_5_.addChatComponentMessage(DCHUtils.getChatMessageFromText("Current Value " + entity.isActive() +
                " id: " + entity.getCustomField() + " att coord : X: "+p_149727_2_+" Y: :"+p_149727_3_+" Z: "+p_149727_4_+""));
        return super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);

    }


    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public boolean isNormalCube() {
        return super.isNormalCube();
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_) {
        RedisValueEntity entity  = (RedisValueEntity) p_149748_1_.getTileEntity(p_149748_2_, p_149748_3_, p_149748_4_);
        if (entity.isActive()) {
            return 15;
        }
        return 0;
    }

    public static void register() {
        GameRegistry.registerBlock(
                craftingRedStoneConnector = new CraftingRedStoneConnector(),
                craftingRedStoneConnector.getUnlocalizedName()
        );
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
        RedisValueEntity entity = (RedisValueEntity) p_149709_1_.getTileEntity(p_149709_2_, p_149709_3_, p_149709_4_);
        if (entity.isActive()) {
            return 15;
        }
        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new RedisValueEntity();
    }


}
