package com.digitalcraftinghabitat.forgemod.datahub.client;

import java.util.Arrays;

/**
 * Created by christopher on 25/08/15.
 */
public class DatahubClientConnector {

    public DatahubClientConnector() {

    }

    public String getSringValueForKey(String key){
        return "demoString";
    }

    public int getIntValueForKey(String key){
        return 10;
    }

    public float getFloatValueForKey(String key){
        return 3.0f;
    }

    public String[] getArrayForKey(String key){
        String[] returnArray =  {"a", "b"};
        return returnArray;
    }
}
