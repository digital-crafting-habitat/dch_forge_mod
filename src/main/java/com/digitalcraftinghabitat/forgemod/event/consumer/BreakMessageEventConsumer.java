package com.digitalcraftinghabitat.forgemod.event.consumer;

import com.digitalcraftinghabitat.forgemod.block.BlockCraftium;
import com.digitalcraftinghabitat.forgemod.util.DCHUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.world.BlockEvent;

import java.io.IOException;

/**
 * Created by christopher on 03/08/15.
 */
public class BreakMessageEventConsumer {


    @SubscribeEvent
    public void sendMessage(BlockEvent.BreakEvent event) throws IOException {
        if (event.block instanceof BlockCraftium){
            event.getPlayer().addChatComponentMessage(DCHUtils.getChatMessageFromText("you broke a Craftium Block"));
        }
    }


}
