package com.digitalcraftinghabitat.forgemod.datahub.client;

import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import redis.clients.jedis.Jedis;

import java.util.Arrays;

/**
 * Created by christopher on 25/08/15.
 */
public class DatahubClientConnector {

    private Jedis jedis;

    public DatahubClientConnector() {
        jedis = new Jedis("85.214.235.74");
        jedis.auth("DCH-Rocks-2015@");
    }

    public String getSringValueForKey(String key) {
        return "demoString";
    }

    public int getIntValueForKey(String valueKey) {
        String returnedValue = jedis.get(valueKey);

        if ((returnedValue != null) && (returnedValue.length() > 0)) {
            int parsedIntegerValue = Integer.parseInt(returnedValue);
            return parsedIntegerValue;
        }

        DCHLog.warning("Returned Redis Value for Key " + valueKey + " was empty");
        return -1;


    }

    public float getFloatValueForKey(String key) {
        return 3.0f;
    }

    public String[] getArrayForKey(String key) {
        String[] returnArray = {"a", "b"};
        return returnArray;
    }

    public void setIntValueForKey(String key, int value) {
        jedis.set(key, String.valueOf(value));
    }

    public static void main(String args[]){
        Jedis jedis = new Jedis("85.214.235.74");
        jedis.auth("DCH-Rocks-2015@");
        String redstone_value = jedis.get("redstone_value");
        System.out.println(redstone_value);
    }
}
