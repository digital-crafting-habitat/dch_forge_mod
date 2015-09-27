package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import com.digitalcraftinghabitat.forgemod.event.consumer.ValueUpdateEvent;
import com.digitalcraftinghabitat.forgemod.util.DCHConfiguration;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.Iterator;
import java.util.List;

/**
 * Created by christopher on 27/09/15.
 */
public class ControllerRefreshThread implements Runnable{

    DCHConfiguration dchConfiguration;
    ValueUpdateEvent valueUpdateEvent;
    private World world;
    static Thread runner;

    public ControllerRefreshThread(World world) {
        this.world = world;
        dchConfiguration = DCHConfiguration.getInstance();
    }



    @Override
    public void run() {
        DatahubClientConnector connector = new DatahubClientConnector();
        int keyValue;
        ScanParams params = new ScanParams();

        params.match(dchConfiguration.getJedis_prefix() + "*");

        ScanResult<String> scanResult = connector.getJedis().scan("0", params);
        List<String> keys = scanResult.getResult();
        while (true) {

            Iterator<String> keyIterator = keys.iterator();

            while (keyIterator.hasNext()) {
                String id = keyIterator.next();
                keyValue = connector.getIntValueForKey(id);

                valueUpdateEvent = new ValueUpdateEvent(this.world);
                valueUpdateEvent.setValue(keyValue);
                valueUpdateEvent.setId(id);

                MinecraftForge.EVENT_BUS.post(valueUpdateEvent);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void init(World world) {
        if (runner != null){
            runner = new Thread(new ControllerRefreshThread(world));
            runner.start();
        }
    }
}


