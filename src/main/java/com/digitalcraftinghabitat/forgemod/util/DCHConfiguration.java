package com.digitalcraftinghabitat.forgemod.util;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by christopher on 29/08/15.
 */
public class DCHConfiguration {

    private String jedisAuth;
    private Configuration config;
    private static DCHConfiguration instance;
    private String jedisUrl;

    private DCHConfiguration(File suggestedConfigurationFile) {
        DCHLog.info("---- load Config File: " + suggestedConfigurationFile.getAbsolutePath());
        config = new Configuration(suggestedConfigurationFile);
        if (suggestedConfigurationFile != null){
            if (suggestedConfigurationFile.exists()){
                config.load();
                jedisUrl = config.get(Configuration.CATEGORY_GENERAL, "jedis_url", "85.214.235.74").getString();
                jedisAuth = config.get(Configuration.CATEGORY_GENERAL, "jedis_auth", "DCH-Rocks-2015@").getString();
                config.save();
            }
        }
        System.out.println("test");
    }

    public static DCHConfiguration getInstanceWithFile(File suggestedConfigurationFile){
        if (instance == null){
            instance = new DCHConfiguration(suggestedConfigurationFile);
        }
        return instance;
    }

    public static DCHConfiguration getInstance(){
        if (instance == null){
            DCHLog.warning("config not initialized");
        }
        return instance;
    }


    public Configuration getConfig() {
        return config;
    }

    public String getJedisUrl() {
        return jedisUrl;
    }

    public String getJedisAuth() {
        return jedisAuth;
    }
}
