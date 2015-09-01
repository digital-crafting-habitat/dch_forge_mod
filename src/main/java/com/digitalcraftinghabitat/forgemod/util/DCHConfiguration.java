package com.digitalcraftinghabitat.forgemod.util;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by christopher on 29/08/15.
 */
public class DCHConfiguration {


    Configuration config;

    public DCHConfiguration(File suggestedConfigurationFile) {
        DCHLog.info("---- load Config File: " + suggestedConfigurationFile.getAbsolutePath());
        config = new Configuration(suggestedConfigurationFile);
        config.load();
    }

    public Configuration getConfig() {
        return config;
    }
}
