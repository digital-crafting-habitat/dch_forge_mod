package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by christopher on 11/08/15.
 */
public class EnergyTile extends TileEntity implements IInventory {

    public int customField;

    public EnergyTile() {
        super();
    }

    @Override
    public int getSizeInventory() {
        return 64;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return new ItemStack(Items.brewing_stand);
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return new ItemStack(Items.chainmail_boots);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return new ItemStack(Items.diamond_boots);
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
        DCHLog.info("setInventorySlotContents");
    }

    @Override
    public String getInventoryName() {
        return "Crafting Energy";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {
        DCHLog.info("openInventory");
    }

    @Override
    public void closeInventory() {
        DCHLog.info("closeInventory");
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setInteger("customField", customField);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        this.customField = par1.getInteger("customField");
    }


}
