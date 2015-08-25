package com.digitalcraftinghabitat.forgemod;

import com.digitalcraftinghabitat.forgemod.block.*;
import com.digitalcraftinghabitat.forgemod.coremod.CraftCommand;
import com.digitalcraftinghabitat.forgemod.event.consumer.BreakMessageEventConsumer;
import com.digitalcraftinghabitat.forgemod.item.DustCraftium;
import com.digitalcraftinghabitat.forgemod.item.ItemCraftiumCoal;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = RefStrings.MODID, version = RefStrings.VERSION)
public class DigitalCraftingHabitatMod {

    @EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        DustCraftium.register();
        ItemCraftiumCoal.register();
        BlockController.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code

        MinecraftForge.EVENT_BUS.register(new BreakMessageEventConsumer());
        GameRegistry.registerBlock(new PistonBase(false), "crafting_piston");
        GameRegistry.registerBlock(new PistonExtension(), "piston_moving");
        GameRegistry.registerBlock(new PistonMoving(), "piston_head");
        GameRegistry.registerBlock(new EnergyBlock(), "energy_block");
        GameRegistry.registerBlock(new BlockCraftium(), "craftium_block");
        GameRegistry.registerBlock(new CraftingLever(), "crafting_lever_block");
        GameRegistry.registerTileEntity(EnergyTile.class, "energy_tile");
        //GameRegistry.registerItem(new ItemCraftiumCoal(), "craftiumCoal");
        ItemCraftiumCoal.addRecipes();
    }

    @EventHandler
    public void init(FMLEvent event) {

    }


    @EventHandler
    public void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new CraftCommand());
    }


}
