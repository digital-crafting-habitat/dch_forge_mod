package com.digitalcraftinghabitat.forgemod.tileentity;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.block.CraftingRedStoneConnector;
import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Rauca on 26.08.2015.
 */
public class RedisValueEntity extends TileEntity {
    DatahubClientConnector datahubClientConnector;
    public int customField;

    public RedisValueEntity(int customField) {
        this.customField = customField;
    }

    public static void init() {
        GameRegistry.registerTileEntity(RedisValueEntity.class, RefStrings.MODID + "redis_tile_entity");
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote)
            return;
        processDatahubValue();
        worldObj.notifyBlockChange(xCoord, yCoord, zCoord, blockType);
    }

    private void processDatahubValue() {
        String key = "redstone_value";

        CraftingRedStoneConnector.isActive = (datahubClientConnector.getIntValueForKey(key) == 1);
    }

    @Override
    public void writeToNBT(NBTTagCompound par1)
    {
        super.writeToNBT(par1);
        par1.setInteger("customField", customField);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1)
    {
        super.readFromNBT(par1);

        par1.getString("id");
        par1.getInteger("z");
        par1.getInteger("z");
        par1.getInteger("z");
        this.customField = par1.getInteger("customField");
    }

}
