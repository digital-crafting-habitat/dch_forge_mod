package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import com.digitalcraftinghabitat.forgemod.util.DCHUtils;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.BlockCompressedPowered;
import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by christopher on 25/08/15.
 */
public class CraftingRedStoneConnector extends BlockCompressedPowered {

    DatahubClientConnector datahubClientConnector = new DatahubClientConnector();
    boolean isActive = true;
    public static Block craftingRedStoneConnector;

    public CraftingRedStoneConnector() {
        super(MapColor.tntColor);
        setBlockTextureName(RefStrings.MODID + ":ore/Craftstone");
        setBlockName("crafting_redstone");
        setCreativeTab(CreativeTabs.tabRedstone);
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

    @Override
    public boolean onBlockActivated(World world, int xCoord, int yCoord, int zCoord,
                                    EntityPlayer player,
                                    int lookAtX, float lookAtY, float lookAtZ,
                                    float p_149727_9_) {
        if (world.isRemote) {
            return true;
        }
        String key = "redstone_value";
        int testkey = datahubClientConnector.getIntValueForKey(key);
        player.addChatComponentMessage(DCHUtils.getChatMessageFromText("Value: " + testkey));
        if (testkey == 0){
            isActive = false;
        }
        else if (testkey == -1){
            isActive = false;
            player.addChatComponentMessage(
                    DCHUtils.getChatMessageFromText("Error while getting the Value for Key " + key)
            );
        }
        else{
            isActive = true;
        }

        world.notifyBlockChange(xCoord, yCoord, zCoord, this);
        return isActive;
    }

    public static void register() {
        GameRegistry.registerBlock(
                craftingRedStoneConnector = new CraftingRedStoneConnector(),
                craftingRedStoneConnector.getUnlocalizedName()
        );
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
        if (isActive) {
            return 15;

        }
        return 0;
    }

}
