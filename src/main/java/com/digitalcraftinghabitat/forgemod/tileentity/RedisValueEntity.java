package com.digitalcraftinghabitat.forgemod.tileentity;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.block.CraftingRedStoneConnector;
import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Rauca on 26.08.2015.
 */
public class RedisValueEntity extends TileEntity {
    DatahubClientConnector datahubClientConnector = new DatahubClientConnector();

    public static void init() {
        GameRegistry.registerTileEntity(RedisValueEntity.class, RefStrings.MODID + "redis_tile_entity");
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote)
            return;
        int testkey = datahubClientConnector.getIntValueForKey("testkey");
        CraftingRedStoneConnector.isActive = (testkey == 1);
        worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, this.blockType);
    }

}
