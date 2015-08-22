package com.digitalcraftinghabitat.forgemod.block;

import buildcraft.core.lib.utils.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.Facing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import com.digitalcraftinghabitat.forgemod.block.PistonExtension;
import net.minecraftforge.common.util.ForgeDirection;


/**
 * Created by admin on 20.08.15.
 */
public class PistonBase extends BlockPistonBase{
    EnergyTile energyTile = null;
    private final boolean isSticky;
    @SideOnly(Side.CLIENT)
    public PistonBase(boolean p_i45443_1_) {
        super(p_i45443_1_);
        this.isSticky = p_i45443_1_;
        setBlockName("crafting_piston");
        setCreativeTab(CreativeTabs.tabRedstone);


    }
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new EnergyTile();
    }

    public EnergyTile getEnergyTile() {
        return energyTile;
    }

    public void setEnergyTile(EnergyTile energyTile) {
        this.energyTile = energyTile;
    }

    @Override public boolean onBlockActivated(World world, int p_149727_2_,
                                    int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_,
                                    int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.isRemote){
            return true;
        }
        Vec3 position = p_149727_5_.getPosition(1.0F);
        ItemStack itemStack = new ItemStack(Items.redstone);
        EntityItem toolItem = new EntityItem(world,p_149727_2_,p_149727_3_,p_149727_4_,itemStack);
        Utils.addToRandomInjectableAround(world, p_149727_2_, p_149727_3_, p_149727_4_, ForgeDirection.UNKNOWN, itemStack);
        return true;
    }

    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        energyTile = new EnergyTile();
        return energyTile;
    }
     private static boolean canPushBlock(Block p_150080_0_, World p_150080_1_, int p_150080_2_, int p_150080_3_, int p_150080_4_, boolean p_150080_5_) {
        if(p_150080_0_ == Blocks.obsidian) {
            return false;
        } else {
            if(p_150080_0_ != Blocks.piston && p_150080_0_ != Blocks.sticky_piston) {
                if(p_150080_0_.getBlockHardness(p_150080_1_, p_150080_2_, p_150080_3_, p_150080_4_) == -1.0F) {
                    return false;
                }

                if(p_150080_0_.getMobilityFlag() == 2) {
                    return false;
                }

                if(p_150080_0_.getMobilityFlag() == 1) {
                    if(!p_150080_5_) {
                        return false;
                    }

                    return true;
                }
            } else if(isExtended(p_150080_1_.getBlockMetadata(p_150080_2_, p_150080_3_, p_150080_4_))) {
                return false;
            }

            return !p_150080_1_.getBlock(p_150080_2_, p_150080_3_, p_150080_4_).hasTileEntity(p_150080_1_.getBlockMetadata(p_150080_2_, p_150080_3_, p_150080_4_));
        }
    }
      private boolean tryExtend(World p_150079_1_, int p_150079_2_, int p_150079_3_, int p_150079_4_, int p_150079_5_) {
        int i1 = p_150079_2_ + Facing.offsetsXForSide[p_150079_5_];
        int j1 = p_150079_3_ + Facing.offsetsYForSide[p_150079_5_];
        int k1 = p_150079_4_ + Facing.offsetsZForSide[p_150079_5_];
        int l1 = 0;

        while(true) {
            if(l1 < 13) {
                if(j1 <= 0 || j1 >= p_150079_1_.getHeight()) {
                    return false;
                }

                Block k3 = p_150079_1_.getBlock(i1, j1, k1);
                if(!k3.isAir(p_150079_1_, i1, j1, k1)) {
                    if(!canPushBlock(k3, p_150079_1_, i1, j1, k1, true)) {
                        return false;
                    }

                    if(k3.getMobilityFlag() != 1) {
                        if(l1 == 12) {
                            return false;
                        }

                        i1 += Facing.offsetsXForSide[p_150079_5_];
                        j1 += Facing.offsetsYForSide[p_150079_5_];
                        k1 += Facing.offsetsZForSide[p_150079_5_];
                        ++l1;
                        continue;
                    }

                    float i2 = k3 instanceof BlockSnow ?-1.0F:1.0F;
                    k3.dropBlockAsItemWithChance(p_150079_1_, i1, j1, k1, p_150079_1_.getBlockMetadata(i1, j1, k1), i2, 0);
                    p_150079_1_.setBlockToAir(i1, j1, k1);
                }
            }

            l1 = i1;
            int var20 = j1;
            int var19 = k1;
            int j2 = 0;

            Block[] ablock;
            int k2;
            int l2;
            int i3 = 0;
            for(ablock = new Block[13]; i1 != p_150079_2_ || j1 != p_150079_3_ || k1 != p_150079_4_; k1 = i3) {
                k2 = i1 - Facing.offsetsXForSide[p_150079_5_];
                l2 = j1 - Facing.offsetsYForSide[p_150079_5_];
                i3 = k1 - Facing.offsetsZForSide[p_150079_5_];
                Block block1 = p_150079_1_.getBlock(k2, l2, i3);
                int j3 = p_150079_1_.getBlockMetadata(k2, l2, i3);
                if(block1 == this && k2 == p_150079_2_ && l2 == p_150079_3_ && i3 == p_150079_4_) {
                    p_150079_1_.setBlock(i1, j1, k1, Blocks.piston_extension, p_150079_5_ | (this.isSticky?8:0), 4);
                    p_150079_1_.setTileEntity(i1, j1, k1, PistonMoving.getTileEntity(Blocks.piston_head, p_150079_5_ | (this.isSticky?8:0), p_150079_5_, true, false));
                } else {
                    p_150079_1_.setBlock(i1, j1, k1, Blocks.piston_extension, j3, 4);
                    p_150079_1_.setTileEntity(i1, j1, k1, PistonMoving.getTileEntity(block1, j3, p_150079_5_, true, false));
                }

                ablock[j2++] = block1;
                i1 = k2;
                j1 = l2;
            }

            i1 = l1;
            j1 = var20;
            k1 = var19;

            for(j2 = 0; i1 != p_150079_2_ || j1 != p_150079_3_ || k1 != p_150079_4_; k1 = i3) {
                k2 = i1 - Facing.offsetsXForSide[p_150079_5_];
                l2 = j1 - Facing.offsetsYForSide[p_150079_5_];
                i3 = k1 - Facing.offsetsZForSide[p_150079_5_];
                p_150079_1_.notifyBlocksOfNeighborChange(k2, l2, i3, ablock[j2++]);
                i1 = k2;
                j1 = l2;
            }

            return true;
        }

    }
    private boolean isIndirectlyPowered(World p_150072_1_, int p_150072_2_, int p_150072_3_, int p_150072_4_, int p_150072_5_) {
        return p_150072_5_ != 0 && p_150072_1_.getIndirectPowerOutput(p_150072_2_, p_150072_3_ - 1, p_150072_4_, 0)?true:(p_150072_5_ != 1 && p_150072_1_.getIndirectPowerOutput(p_150072_2_, p_150072_3_ + 1, p_150072_4_, 1)?true:(p_150072_5_ != 2 && p_150072_1_.getIndirectPowerOutput(p_150072_2_, p_150072_3_, p_150072_4_ - 1, 2)?true:(p_150072_5_ != 3 && p_150072_1_.getIndirectPowerOutput(p_150072_2_, p_150072_3_, p_150072_4_ + 1, 3)?true:(p_150072_5_ != 5 && p_150072_1_.getIndirectPowerOutput(p_150072_2_ + 1, p_150072_3_, p_150072_4_, 5)?true:(p_150072_5_ != 4 && p_150072_1_.getIndirectPowerOutput(p_150072_2_ - 1, p_150072_3_, p_150072_4_, 4)?true:(p_150072_1_.getIndirectPowerOutput(p_150072_2_, p_150072_3_, p_150072_4_, 0)?true:(p_150072_1_.getIndirectPowerOutput(p_150072_2_, p_150072_3_ + 2, p_150072_4_, 1)?true:(p_150072_1_.getIndirectPowerOutput(p_150072_2_, p_150072_3_ + 1, p_150072_4_ - 1, 2)?true:(p_150072_1_.getIndirectPowerOutput(p_150072_2_, p_150072_3_ + 1, p_150072_4_ + 1, 3)?true:(p_150072_1_.getIndirectPowerOutput(p_150072_2_ - 1, p_150072_3_ + 1, p_150072_4_, 4)?true:p_150072_1_.getIndirectPowerOutput(p_150072_2_ + 1, p_150072_3_ + 1, p_150072_4_, 5)))))))))));
    }
  @Override  public boolean onBlockEventReceived(World p_149696_1_, int p_149696_2_, int p_149696_3_, int p_149696_4_, int p_149696_5_, int p_149696_6_) {
        if (!p_149696_1_.isRemote) {
            boolean tileentity1 = this.isIndirectlyPowered(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_6_);
            if (tileentity1 && p_149696_5_ == 1) {
                p_149696_1_.setBlockMetadataWithNotify(p_149696_2_, p_149696_3_, p_149696_4_, p_149696_6_ | 8, 2);
                return false;
            }

            if (!tileentity1 && p_149696_5_ == 0) {
                return false;
            }
        }

        if (p_149696_5_ == 0) {
            if (!this.tryExtend(p_149696_1_, p_149696_2_, p_149696_3_, p_149696_4_, p_149696_6_)) {
                return false;
            }

            p_149696_1_.setBlockMetadataWithNotify(p_149696_2_, p_149696_3_, p_149696_4_, p_149696_6_ | 8, 2);
            p_149696_1_.playSoundEffect((double) p_149696_2_ + 0.5D, (double) p_149696_3_ + 0.5D, (double) p_149696_4_ + 0.5D, "tile.piston.out", 0.5F, p_149696_1_.rand.nextFloat() * 0.25F + 0.6F);
        } else if (p_149696_5_ == 1) {
            TileEntity tileentity11 = p_149696_1_.getTileEntity(p_149696_2_ + Facing.offsetsXForSide[p_149696_6_], p_149696_3_ + Facing.offsetsYForSide[p_149696_6_], p_149696_4_ + Facing.offsetsZForSide[p_149696_6_]);
            if (tileentity11 instanceof TileEntityPiston) {
                ((TileEntityPiston) tileentity11).clearPistonTileEntity();
            }

            p_149696_1_.setBlock(p_149696_2_, p_149696_3_, p_149696_4_, Blocks.piston_extension, p_149696_6_, 3);
            p_149696_1_.setTileEntity(p_149696_2_, p_149696_3_, p_149696_4_, PistonMoving.getTileEntity(this, p_149696_6_, p_149696_6_, false, true));
            if (this.isSticky) {
                int j1 = p_149696_2_ + Facing.offsetsXForSide[p_149696_6_] * 2;
                int k1 = p_149696_3_ + Facing.offsetsYForSide[p_149696_6_] * 2;
                int l1 = p_149696_4_ + Facing.offsetsZForSide[p_149696_6_] * 2;
                Block block = p_149696_1_.getBlock(j1, k1, l1);
                int i2 = p_149696_1_.getBlockMetadata(j1, k1, l1);
                boolean flag1 = false;
                if (block == Blocks.piston_extension) {
                    TileEntity tileentity = p_149696_1_.getTileEntity(j1, k1, l1);
                    if (tileentity instanceof TileEntityPiston) {
                        TileEntityPiston tileentitypiston = (TileEntityPiston) tileentity;
                        if (tileentitypiston.getPistonOrientation() == p_149696_6_ && tileentitypiston.isExtending()) {
                            tileentitypiston.clearPistonTileEntity();
                            block = tileentitypiston.getStoredBlockID();
                            i2 = tileentitypiston.getBlockMetadata();
                            flag1 = true;
                        }
                    }
                }

                if (!flag1 && block.getMaterial() != Material.air && canPushBlock(block, p_149696_1_, j1, k1, l1, false) && (block.getMobilityFlag() == 0 || block == Blocks.piston || block == Blocks.sticky_piston)) {
                    p_149696_2_ += Facing.offsetsXForSide[p_149696_6_];
                    p_149696_3_ += Facing.offsetsYForSide[p_149696_6_];
                    p_149696_4_ += Facing.offsetsZForSide[p_149696_6_];
                    p_149696_1_.setBlock(p_149696_2_, p_149696_3_, p_149696_4_, Blocks.piston_extension, i2, 3);
                    p_149696_1_.setTileEntity(p_149696_2_, p_149696_3_, p_149696_4_, PistonMoving.getTileEntity(block, i2, p_149696_6_, false, false));
                    p_149696_1_.setBlockToAir(j1, k1, l1);
                } else if (!flag1) {
                    p_149696_1_.setBlockToAir(p_149696_2_ + Facing.offsetsXForSide[p_149696_6_], p_149696_3_ + Facing.offsetsYForSide[p_149696_6_], p_149696_4_ + Facing.offsetsZForSide[p_149696_6_]);
                }
            } else {
                p_149696_1_.setBlockToAir(p_149696_2_ + Facing.offsetsXForSide[p_149696_6_], p_149696_3_ + Facing.offsetsYForSide[p_149696_6_], p_149696_4_ + Facing.offsetsZForSide[p_149696_6_]);
            }

            p_149696_1_.playSoundEffect((double) p_149696_2_ + 0.5D, (double) p_149696_3_ + 0.5D, (double) p_149696_4_ + 0.5D, "tile.piston.in", 0.5F, p_149696_1_.rand.nextFloat() * 0.15F + 0.6F);
        }

        return true;
    }






}
