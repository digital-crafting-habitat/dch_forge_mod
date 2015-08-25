package com.digitalcraftinghabitat.forgemod;

import com.digitalcraftinghabitat.forgemod.block.*;
import com.digitalcraftinghabitat.forgemod.event.consumer.BreakMessageEventConsumer;
import com.digitalcraftinghabitat.forgemod.coremod.CraftCommand;
import com.digitalcraftinghabitat.forgemod.item.CraftingHabitatItems;
import com.digitalcraftinghabitat.forgemod.item.DustCraftium;
import com.digitalcraftinghabitat.forgemod.item.ItemCraftiumCoal;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = DigitalCraftingHabitatMod.MODID, version = DigitalCraftingHabitatMod.VERSION)
public class DigitalCraftingHabitatMod {
    public static final String MODID = "digitalcraftinghabitat";
    public static final String VERSION = "1.0";


    @EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        DustCraftium.mainRegistry();
        ItemCraftiumCoal.mainRegistry();
        BlockController.mainRegistry();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        MinecraftForge.EVENT_BUS.register(new BreakMessageEventConsumer());
        GameRegistry.registerBlock(new EnergyBlock(), "energy_block");
        GameRegistry.registerBlock(new BlockCraftium(), "craftium_block");
        GameRegistry.registerBlock(new CraftingLever(), "crafting_lever_block");
        GameRegistry.registerTileEntity(EnergyTile.class, "energy_tile");
    }

    @EventHandler
    public void init(FMLEvent event) {


    }



    @EventHandler
    public void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new CraftCommand());
    }



}
