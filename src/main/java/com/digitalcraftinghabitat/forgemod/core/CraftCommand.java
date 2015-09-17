package com.digitalcraftinghabitat.forgemod.core;

import com.digitalcraftinghabitat.forgemod.tileentity.RedisValueEntity;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christopher on 09/08/15.
 */
public class CraftCommand implements ICommand {


    @Override
    public String getCommandName() {
        return "craft";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/craft set_id <X> <Y> <Z> newID (must be numeric)";
    }

    @Override
    public List getCommandAliases() {
        List getEnergy = new ArrayList();
        getEnergy.add("craft");
        return getEnergy;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] p_71515_2_) {
        if ((p_71515_2_ != null) && (p_71515_2_.length > 4)) {
            if (p_71515_2_[0].equals("set_id")) {
                DCHLog.warning("set id called");
                DCHLog.warning("coords X: " + p_71515_2_[1] + " Y: " + p_71515_2_[2] + " Z: " + p_71515_2_[3]);
                int x = Integer.parseInt(p_71515_2_[1]);
                int y = Integer.parseInt(p_71515_2_[2]);
                int z = Integer.parseInt(p_71515_2_[3]);
                TileEntity tileEntity = sender.getEntityWorld().getTileEntity(x, y, z);
                if ((tileEntity != null) && (tileEntity instanceof RedisValueEntity)) {
                    try {
                        RedisValueEntity redisValueEntity = (RedisValueEntity) sender.getEntityWorld().getTileEntity(x, y, z);
                        if ((p_71515_2_[4] != null) && (!p_71515_2_[4].isEmpty())) {
                            try {
                                redisValueEntity.setCustomField(Integer.parseInt(p_71515_2_[4]));
                                DCHLog.warning("set new id " + p_71515_2_[4] + " to entity");
                            } catch (NumberFormatException e) {
                                DCHLog.error(e);
                            }
                        }
                    } catch (ClassCastException e) {
                        DCHLog.error(e);
                    }
                } else {
                    if ((tileEntity == null)) {
                        DCHLog.warning("tile at coords is null");
                    }
                }

            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
