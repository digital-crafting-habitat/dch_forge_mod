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
import net.minecraftforge.common.util.ForgeDirection;


/**
 * Created by admin on 20.08.15.
 */
public class PistonBase extends BlockPistonBase {
    PistonTile pistonTile = null;
    private final boolean isSticky;

    @SideOnly(Side.CLIENT)
    public PistonBase(boolean isSticky) {
        super(isSticky);
        this.isSticky = isSticky;
        setBlockName("crafting_piston");
        setCreativeTab(CreativeTabs.tabRedstone);


    }

    public PistonTile createTileEntity(World world, int metadata) {
        return new PistonTile();
    }

    public PistonTile getPistonTile() {
        return pistonTile;
    }

    public void setPistonTile(PistonTile pistonTile) {
        this.pistonTile = pistonTile;
    }

    @Override
    public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player,
                                    int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.isRemote) {
            return true;
        }
        Vec3 position = player.getPosition(1.0F);
        ItemStack itemStack = new ItemStack(Items.redstone);
        EntityItem toolItem = new EntityItem(world, posX, posY, posZ, itemStack);
        Utils.addToRandomInjectableAround(world, posX, posY, posZ, ForgeDirection.UNKNOWN, itemStack);
        return true;
    }

    public TileEntity createNewTileEntity(World world, int blockSide) {
        pistonTile = createTileEntity(world, blockSide);
        return pistonTile;
    }

    private static boolean canPushBlock(Block block, World world, int posX, int posY, int posZ, boolean isSticky) {
        if (block == Blocks.obsidian) {
            return false;
        } else {
            if (block != Blocks.piston && block != Blocks.sticky_piston) {
                if (block.getBlockHardness(world, posX, posY, posZ) == -1.0F) {
                    return false;
                }

                if (block.getMobilityFlag() == 2) {
                    return false;
                }

                if (block.getMobilityFlag() == 1) {
                    if (!isSticky) {
                        return false;
                    }

                    return true;
                }
            } else if (isExtended(world.getBlockMetadata(posX, posY, posZ))) {
                return false;
            }

            return !world.getBlock(posX, posY, posZ).hasTileEntity(world.getBlockMetadata(posX, posY, posZ));
        }
    }

    private boolean tryExtend(World world, int posX, int posY, int posZ, int blockSide) {
        int i1 = posX + Facing.offsetsXForSide[blockSide];
        int j1 = posY + Facing.offsetsYForSide[blockSide];
        int k1 = posZ + Facing.offsetsZForSide[blockSide];
        int l1 = 0;

        while (true) {
            if (l1 < 13) {
                if (j1 <= 0 || j1 >= world.getHeight()) {
                    return false;
                }

                Block k3 = world.getBlock(i1, j1, k1);
                if (!k3.isAir(world, i1, j1, k1)) {
                    if (!canPushBlock(k3, world, i1, j1, k1, true)) {
                        return false;
                    }

                    if (k3.getMobilityFlag() != 1) {
                        if (l1 == 12) {
                            return false;
                        }

                        i1 += Facing.offsetsXForSide[blockSide];
                        j1 += Facing.offsetsYForSide[blockSide];
                        k1 += Facing.offsetsZForSide[blockSide];
                        ++l1;
                        continue;
                    }

                    float i2 = k3 instanceof BlockSnow ? -1.0F : 1.0F;
                    k3.dropBlockAsItemWithChance(world, i1, j1, k1, world.getBlockMetadata(i1, j1, k1), i2, 0);
                    world.setBlockToAir(i1, j1, k1);
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
            for (ablock = new Block[13]; i1 != posX || j1 != posY || k1 != posZ; k1 = i3) {
                k2 = i1 - Facing.offsetsXForSide[blockSide];
                l2 = j1 - Facing.offsetsYForSide[blockSide];
                i3 = k1 - Facing.offsetsZForSide[blockSide];
                Block block1 = world.getBlock(k2, l2, i3);
                int j3 = world.getBlockMetadata(k2, l2, i3);
                if (block1 == this && k2 == posX && l2 == posY && i3 == posZ) {
                    world.setBlock(i1, j1, k1, Blocks.piston_extension, blockSide | (this.isSticky ? 8 : 0), 4);
                    world.setTileEntity(i1, j1, k1, PistonMoving.getTileEntity(Blocks.piston_head, blockSide | (this.isSticky ? 8 : 0), blockSide, true, false));
                } else {
                    world.setBlock(i1, j1, k1, Blocks.piston_extension, j3, 4);
                    world.setTileEntity(i1, j1, k1, PistonMoving.getTileEntity(block1, j3, blockSide, true, false));
                }

                ablock[j2++] = block1;
                i1 = k2;
                j1 = l2;
            }

            i1 = l1;
            j1 = var20;
            k1 = var19;

            for (j2 = 0; i1 != posX || j1 != posY || k1 != posZ; k1 = i3) {
                k2 = i1 - Facing.offsetsXForSide[blockSide];
                l2 = j1 - Facing.offsetsYForSide[blockSide];
                i3 = k1 - Facing.offsetsZForSide[blockSide];
                world.notifyBlocksOfNeighborChange(k2, l2, i3, ablock[j2++]);
                i1 = k2;
                j1 = l2;
            }

            return true;
        }

    }

    private boolean isIndirectlyPowered(World world, int posX, int posY, int posZ, int p_150072_5_) {
        return p_150072_5_ != 0 && world.getIndirectPowerOutput(posX, posY - 1, posZ, 0) ? true : (p_150072_5_ != 1 && world.getIndirectPowerOutput(posX, posY + 1, posZ, 1) ? true : (p_150072_5_ != 2 && world.getIndirectPowerOutput(posX, posY, posZ - 1, 2) ? true : (p_150072_5_ != 3 && world.getIndirectPowerOutput(posX, posY, posZ + 1, 3) ? true : (p_150072_5_ != 5 && world.getIndirectPowerOutput(posX + 1, posY, posZ, 5) ? true : (p_150072_5_ != 4 && world.getIndirectPowerOutput(posX - 1, posY, posZ, 4) ? true : (world.getIndirectPowerOutput(posX, posY, posZ, 0) ? true : (world.getIndirectPowerOutput(posX, posY + 2, posZ, 1) ? true : (world.getIndirectPowerOutput(posX, posY + 1, posZ - 1, 2) ? true : (world.getIndirectPowerOutput(posX, posY + 1, posZ + 1, 3) ? true : (world.getIndirectPowerOutput(posX - 1, posY + 1, posZ, 4) ? true : world.getIndirectPowerOutput(posX + 1, posY + 1, posZ, 5)))))))))));
    }

    @Override
    public boolean onBlockEventReceived(World world, int posX, int posY, int posZ, int direction, int blockSide) {
        if (!world.isRemote) {
            boolean tileentity1 = this.isIndirectlyPowered(world, posX, posY, posZ, blockSide);
            if (tileentity1 && direction == 1) {
                world.setBlockMetadataWithNotify(posX, posY, posZ, blockSide | 8, 2);
                return false;
            }

            if (!tileentity1 && direction == 0) {
                return false;
            }
        }

        if (direction == 0) {
            if (!this.tryExtend(world, posX, posY, posZ, blockSide)) {
                return false;
            }

            world.setBlockMetadataWithNotify(posX, posY, posZ, blockSide | 8, 2);
            world.playSoundEffect((double) posX + 0.5D, (double) posY + 0.5D, (double) posZ + 0.5D, "tile.piston.out", 0.5F, world.rand.nextFloat() * 0.25F + 0.6F);
        } else if (direction == 1) {
            TileEntity tileentity11 = world.getTileEntity(posX + Facing.offsetsXForSide[blockSide], posY + Facing.offsetsYForSide[blockSide], posZ + Facing.offsetsZForSide[blockSide]);
            if (tileentity11 instanceof TileEntityPiston) {
                ((TileEntityPiston) tileentity11).clearPistonTileEntity();
            }

            world.setBlock(posX, posY, posZ, Blocks.piston_extension, blockSide, 3);
            world.setTileEntity(posX, posY, posZ, PistonMoving.getTileEntity(this, blockSide, blockSide, false, true));
            if (this.isSticky) {
                int j1 = posX + Facing.offsetsXForSide[blockSide] * 2;
                int k1 = posY + Facing.offsetsYForSide[blockSide] * 2;
                int l1 = posZ + Facing.offsetsZForSide[blockSide] * 2;
                Block block = world.getBlock(j1, k1, l1);
                int i2 = world.getBlockMetadata(j1, k1, l1);
                boolean flag1 = false;
                if (block == Blocks.piston_extension) {
                    TileEntity tileentity = world.getTileEntity(j1, k1, l1);
                    if (tileentity instanceof TileEntityPiston) {
                        TileEntityPiston tileentitypiston = (TileEntityPiston) tileentity;
                        if (tileentitypiston.getPistonOrientation() == blockSide && tileentitypiston.isExtending()) {
                            tileentitypiston.clearPistonTileEntity();
                            block = tileentitypiston.getStoredBlockID();
                            i2 = tileentitypiston.getBlockMetadata();
                            flag1 = true;
                        }
                    }
                }

                if (!flag1 && block.getMaterial() != Material.air && canPushBlock(block, world, j1, k1, l1, false) && (block.getMobilityFlag() == 0 || block == Blocks.piston || block == Blocks.sticky_piston)) {
                    posX += Facing.offsetsXForSide[blockSide];
                    posY += Facing.offsetsYForSide[blockSide];
                    posZ += Facing.offsetsZForSide[blockSide];
                    world.setBlock(posX, posY, posZ, Blocks.piston_extension, i2, 3);
                    world.setTileEntity(posX, posY, posZ, PistonMoving.getTileEntity(block, i2, blockSide, false, false));
                    world.setBlockToAir(j1, k1, l1);
                } else if (!flag1) {
                    world.setBlockToAir(posX + Facing.offsetsXForSide[blockSide], posY + Facing.offsetsYForSide[blockSide], posZ + Facing.offsetsZForSide[blockSide]);
                }
            } else {
                world.setBlockToAir(posX + Facing.offsetsXForSide[blockSide], posY + Facing.offsetsYForSide[blockSide], posZ + Facing.offsetsZForSide[blockSide]);
            }

            world.playSoundEffect((double) posX + 0.5D, (double) posY + 0.5D, (double) posZ + 0.5D, "tile.piston.in", 0.5F, world.rand.nextFloat() * 0.15F + 0.6F);
        }

        return true;
    }


}
