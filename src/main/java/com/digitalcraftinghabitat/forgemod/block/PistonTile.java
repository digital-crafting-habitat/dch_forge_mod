package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityPiston;

/**
 * Created by christopher on 24/08/15.
 */
public class PistonTile extends TileEntityPiston {

    public PistonTile(Block p_i45444_1_, int p_i45444_2_, int p_i45444_3_, boolean p_i45444_4_, boolean p_i45444_5_) {
        super(p_i45444_1_, p_i45444_2_, p_i45444_3_, p_i45444_4_, p_i45444_5_);
        DCHLog.crafting("XXXXX");

    }

    public PistonTile() {
        super();
        DCHLog.crafting("YYYY");
    }

}
