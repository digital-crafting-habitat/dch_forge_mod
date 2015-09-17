package com.digitalcraftinghabitat.forgemod;

import com.digitalcraftinghabitat.forgemod.block.*;
import com.digitalcraftinghabitat.forgemod.core.CraftCommand;
import com.digitalcraftinghabitat.forgemod.event.WorldGen;
import com.digitalcraftinghabitat.forgemod.event.consumer.BreakMessageEventConsumer;
import com.digitalcraftinghabitat.forgemod.item.DustCraftium;
import com.digitalcraftinghabitat.forgemod.item.ItemCraftiumCoal;
import com.digitalcraftinghabitat.forgemod.item.ItemIngotCraftium;
import com.digitalcraftinghabitat.forgemod.util.DCHConfiguration;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = RefStrings.MODID, version = RefStrings.VERSION)
public class DigitalCraftingHabitatMod {

    public DCHConfiguration dchConfiguration;
    public WorldGen worldGen;

    @Instance(value = RefStrings.MODID)
    public static DigitalCraftingHabitatMod instance;

    @EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        DustCraftium.register();
        ItemCraftiumCoal.register();
        ItemIngotCraftium.register();
        BlockController.register();
        OreCraftium.register();
        CraftingRedStoneConnector.register();
        dchConfiguration = DCHConfiguration.getInstanceWithFile(event.getSuggestedConfigurationFile());
        DCHLog.info(dchConfiguration.getConfig().getCategoryNames().toString());
        String someString = dchConfiguration.getConfig()
                .get(Configuration.CATEGORY_GENERAL, "redstoneID", "nothing").getString();
        DCHLog.info(someString);
        worldGen = new WorldGen();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code

        MinecraftForge.EVENT_BUS.register(new BreakMessageEventConsumer());
        GameRegistry.registerBlock(new EnergyBlock(), "energy_block");
        GameRegistry.registerBlock(new BlockCraftium(), "craftium_block");
        GameRegistry.registerBlock(new CraftingLever(), "crafting_lever_block");
        GameRegistry.registerTileEntity(EnergyTile.class, "energy_tile");
        ItemCraftiumCoal.addRecipes();
        ItemIngotCraftium.addRecipes();
        CraftingRedStoneConnector.addRecipes();
        GameRegistry.registerWorldGenerator(worldGen, 1);
        //NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @EventHandler
    public void init(FMLEvent event) {

    }

    @EventHandler
    public void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new CraftCommand());
    }
}