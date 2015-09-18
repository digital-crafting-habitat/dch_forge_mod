package com.digitalcraftinghabitat.forgemod.tileentity;

import com.digitalcraftinghabitat.forgemod.GUI.GuiWindow;
import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Rauca on 26.08.2015.
 */
public class RedisValueEntity extends TileEntity {
    static DatahubClientConnector datahubClientConnector;

    public int customField;// = GuiWindow.dchId; //TODO GUI
    private boolean active;
    private int count;

    public RedisValueEntity() {
        if (datahubClientConnector == null){
            datahubClientConnector = new DatahubClientConnector();
        }
    }

    public static void init() {
        GameRegistry.registerTileEntity(RedisValueEntity.class, RefStrings.MODID + "redis_tile_entity");
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote){
            return;
        }else{
            if(customField > 0){
                if (count >= 25){
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
                customField = count2++;
                //customField = GuiWindow.dchId; //TODO GUI
            }
        }
    }

    private void processDatahubValue() {
        //customField = GuiWindow.dchId; //TODO GUI
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
        par1.setInteger("customField", customField);
        par1.setBoolean("active", active);
        super.writeToNBT(par1);
    }

    private static int count2 = 12;

    @Override
    public void readFromNBT(NBTTagCompound par1)
    {
        this.customField = par1.getInteger("customField");
        if (customField < 1){
            customField = count2++;
        }
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
        readFromNBT(packet.func_148857_g());
    }

}
