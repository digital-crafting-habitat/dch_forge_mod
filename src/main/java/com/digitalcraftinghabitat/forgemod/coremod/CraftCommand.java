package com.digitalcraftinghabitat.forgemod.coremod;

import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

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
        return "/craft - no parameters yet";
    }

    @Override
    public List getCommandAliases() {
        List getEnergy = new ArrayList();
        getEnergy.add("craft");
        return getEnergy;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] p_71515_2_) {
        DCHLog.info("not implemented yet. command sender info: " + sender.toString());
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
