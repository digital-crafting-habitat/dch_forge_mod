package com.digitalcraftinghabitat.forgemod.event.consumer;

import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

/**
 * Created by christopher on 03/08/15.
 */
public class BreakMessageEventConsumer {


   /* @SubscribeEvent
    public void sendMessage(BlockEvent.BreakEvent event) throws IOException {
        if (event.block instanceof BlockCraftium){
            event.getPlayer().addChatComponentMessage(DCHUtils.getChatMessageFromText("You broke a Craftium Block"));
        }
    }*/

    @SubscribeEvent
    public void onCommand(ValueUpdateEvent tce) throws IOException {
        if (tce.getId().equals("id_12")){
            DCHLog.info("get Event with id: " + tce.getId() + " value:" + tce.getValue());
        }
    }

}
