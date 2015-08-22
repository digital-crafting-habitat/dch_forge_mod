package com.digitalcraftinghabitat.forgemod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.init.Blocks;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


/**
 * Created by admin on 20.08.15.
 */
public class PistonExtension extends BlockPistonExtension {
    private IIcon field_150088_a;
    public PistonExtension(){
        setBlockName("piston_head");


    }
    @SideOnly(Side.CLIENT)
    public void func_150086_a(IIcon p_150086_1_) {
        this.field_150088_a = p_150086_1_;
    }
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_getIcon_1_, int p_getIcon_2_) {
        int var3 = getDirectionMeta(p_getIcon_2_);
        return p_getIcon_1_ == var3?(this.field_150088_a != null?this.field_150088_a:((p_getIcon_2_ & 8) != 0?PistonBase.getPistonBaseIcon("piston_top_sticky"):PistonBase.getPistonBaseIcon("piston_top_normal"))):(var3 < 6 && p_getIcon_1_ == Facing.oppositeSide[var3]?PistonBase.getPistonBaseIcon("piston_top_normal"):PistonBase.getPistonBaseIcon("piston_side"));
    }
    @Override public void breakBlock(World p_breakBlock_1_, int p_breakBlock_2_, int p_breakBlock_3_, int p_breakBlock_4_, Block p_breakBlock_5_, int p_breakBlock_6_) {
        super.breakBlock(p_breakBlock_1_, p_breakBlock_2_, p_breakBlock_3_, p_breakBlock_4_, p_breakBlock_5_, p_breakBlock_6_);
        int var7 = Facing.oppositeSide[getDirectionMeta(p_breakBlock_6_)];
        p_breakBlock_2_ += Facing.offsetsXForSide[var7];
        p_breakBlock_3_ += Facing.offsetsYForSide[var7];
        p_breakBlock_4_ += Facing.offsetsZForSide[var7];
        Block var8 = p_breakBlock_1_.getBlock(p_breakBlock_2_, p_breakBlock_3_, p_breakBlock_4_);
        if(var8 == Blocks.piston || var8 == Blocks.sticky_piston) {
            p_breakBlock_6_ = p_breakBlock_1_.getBlockMetadata(p_breakBlock_2_, p_breakBlock_3_, p_breakBlock_4_);
            if(PistonBase.isExtended(p_breakBlock_6_)) {
                var8.dropBlockAsItem(p_breakBlock_1_, p_breakBlock_2_, p_breakBlock_3_, p_breakBlock_4_, p_breakBlock_6_, 0);
                p_breakBlock_1_.setBlockToAir(p_breakBlock_2_, p_breakBlock_3_, p_breakBlock_4_);
            }
        }

    }



}
