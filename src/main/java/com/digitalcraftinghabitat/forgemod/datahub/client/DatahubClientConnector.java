package com.digitalcraftinghabitat.forgemod.datahub.client;

import com.digitalcraftinghabitat.forgemod.util.DCHConfiguration;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import redis.clients.jedis.Jedis;

import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by christopher on 25/08/15.
 */
public class DatahubClientConnector {

    private Jedis jedis;

    public DatahubClientConnector() {
        DCHConfiguration dchConfiguration = DCHConfiguration.getInstance();
        jedis = new Jedis(dchConfiguration.getJedisUrl());
        jedis.auth(dchConfiguration.getJedisAuth());
        //jedis = new Jedis("192.168.99.100", 32768);

        jedis.getClient().setConnectionTimeout(40000);
        jedis.getClient().setSoTimeout(40000);
        jedis.getClient().setConnectionTimeout(40000);
    }

    public String getSringValueForKey(String key) {
        return "demoString";
    }

    public int getIntValueForKey(String valueKey) {
        try {
            String returnedValue = jedis.get(valueKey);

            if ((returnedValue != null) && (returnedValue.length() > 0)) {
                int parsedIntegerValue = Integer.parseInt(returnedValue.replaceAll("\\D", ""));
                // DCHLog.warning("Returned Redis Value for Key " + valueKey + " was " + parsedIntegerValue);
                return parsedIntegerValue;
            }
            else{
                // DCHLog.warning("Returned Redis Value for Key " + valueKey + " was empty");
            }
        }
        catch (Exception e){
            DCHLog.error(e);
        }
        return -1;
    }

    public void setValueForKey(String valueKey, String value) {
        jedis.set(valueKey, value);
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
