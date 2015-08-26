package com.digitalcraftinghabitat.forgemod.datahub.client;

import redis.clients.jedis.Jedis;

import java.util.Arrays;

/**
 * Created by christopher on 25/08/15.
 */
public class DatahubClientConnector {

    private Jedis jedis;

    public DatahubClientConnector() {
        jedis = new Jedis("85.214.235.74");
    }

    public String getSringValueForKey(String key){
        return "demoString";
    }

    public int getIntValueForKey(String key){
        String redstone_value = jedis.get("redstone_value");
        return Integer.parseInt(redstone_value);
    }

    public float getFloatValueForKey(String key){
        return 3.0f;
    }

    public String[] getArrayForKey(String key){
        String[] returnArray =  {"a", "b"};
        return returnArray;
    }

    public static void main(String args[]){
        Jedis jedis = new Jedis("85.214.235.74");
        String redstone_value = jedis.get("redstone_value");
        System.out.println(redstone_value);
    }
}
