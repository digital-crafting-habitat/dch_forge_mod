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

    public String getIntValueForKey(String key){
        return "demoString";
    }

    public String getFloatValueForKey(String key){
        return "demoString";
    }

    public String[] getArrayForKey(String key){
        String[] returnArray =  {"a", "b"};
        return returnArray;
    }
}
