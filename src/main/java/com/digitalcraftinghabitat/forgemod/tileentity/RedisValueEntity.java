package com.digitalcraftinghabitat.forgemod.tileentity;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.block.CraftingRedStoneConnector;
import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Rauca on 26.08.2015.
 */
public class RedisValueEntity extends TileEntity {
    static DatahubClientConnector datahubClientConnector;
    public int customField;
    private boolean active;
    private int count;

    public RedisValueEntity(int customField) {
        DCHLog.info("new TileEntity with ID " + customField);
        this.customField = customField;
    }

    public static void init() {
        GameRegistry.registerTileEntity(RedisValueEntity.class, RefStrings.MODID + "redis_tile_entity");
        if (datahubClientConnector == null){
            datahubClientConnector = new DatahubClientConnector();
        }

    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote)
            return;
        if (count >= 100){
            DCHLog.info("update value for tile with id " + customField + " actual value: " + active);
            boolean oldValue = active;
            processDatahubValue();
            if (oldValue != active){
                DCHLog.info("new Value detected " + active);
                worldObj.notifyBlockChange(xCoord, yCoord, zCoord, blockType);
            }
            count = 0;
        }
        count++;
    }

    private void processDatahubValue() {
        String key = "id_" + customField;
        int value = datahubClientConnector.getIntValueForKey(key);
        if (value == 1){
            active = true;
        }else {
            active = false;
        }
        DCHLog.info("value for key " + key + " is " + value);
    }

    public boolean isActive (){
        return active;
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
