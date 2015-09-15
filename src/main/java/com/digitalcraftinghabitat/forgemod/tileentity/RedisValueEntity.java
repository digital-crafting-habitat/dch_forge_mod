package com.digitalcraftinghabitat.forgemod.tileentity;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.block.CraftingRedStoneConnector;
import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;

/**
 * Created by Rauca on 26.08.2015.
 */
public class RedisValueEntity extends TileEntity {
    static DatahubClientConnector datahubClientConnector;

    public int customField;
    private boolean active;
    private int count;

    public static void init() {
        GameRegistry.registerTileEntity(RedisValueEntity.class, RefStrings.MODID + "redis_tile_entity");
        if (datahubClientConnector == null){
            datahubClientConnector = new DatahubClientConnector();
        }

    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote){
            return;
        }else{
            if(customField > 0){
                if (count >= 100){
                    DCHLog.info("update value for tile with id " + customField + " actual value: " + active);
                    boolean oldValue = active;
                    processDatahubValue();
                    if (oldValue != active){
                        DCHLog.info("new Value detected " + active);
                    }
                    count = 0;
                    worldObj.notifyBlockChange(xCoord, yCoord, zCoord, blockType);
                }
                count++;
            }else{
                DCHLog.info("Custom Field is null");
                // here should go the code to generate a new Value
                customField = count2++;
            }
        }
    }

    private void processDatahubValue() {
        String key = "id_" + customField;
        int value = datahubClientConnector.getIntValueForKey(key);
        if (value == 1){
            active = true;
        }else {
            active = false;
        }
    }

    public boolean isActive (){
        return active;
    }

    @Override
    public void writeToNBT(NBTTagCompound par1)
    {
        DCHLog.info("VVVVVV: writeToNBT entity Tile with id customField and value " + customField);
        par1.setInteger("customField", customField);
        DCHLog.info("VVVVVV: writeToNBT entity Tile with id active and value " + active);
        par1.setBoolean("active", active);
        super.writeToNBT(par1);
    }

    private static int count2 = 12;

    @Override
    public void readFromNBT(NBTTagCompound par1)
    {
        DCHLog.info("TTTTTT: readFromNBT entity Tile with id customField and value " + customField);
        this.customField = par1.getInteger("customField");
        if (customField < 1){
            //HERE SHOULD GO THE CODE TO LOAD FROM REDIS
            customField = count2++;
            DCHLog.info("TTTTTT: NO value for customField during read, create a new one for entity : " + customField);
        }
        DCHLog.info("TTTTTT: readFromNBT entity Tile with id active and value " + active);
        this.active = par1.getBoolean("active");
        super.readFromNBT(par1);
    }

    public int getCustomField() {
        return customField;
    }

    public void setCustomField(int customField) {
        this.customField = customField;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        DCHLog.info("TTTTTT: onDataPacket called");
        readFromNBT(packet.func_148857_g());
    }

}
