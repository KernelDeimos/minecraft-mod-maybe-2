package com.example.examplemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class RickRollBlock extends Block implements ITileEntityProvider {
    public static final PropertyDirection FACING =
        PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public RickRollBlock() {
        super(Material.ROCK);
        /*
Block.onBlockActivated(
    World worldIn, BlockPos pos, IBlockState state,
    EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
    float hitX, float hitY, float hitZ) : boolean
        */
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new RickRollTE();
    }

    private RickRollTE getTE(World world, BlockPos pos) {
        return (RickRollTE) world.getTileEntity(pos);
    }

    @Override
    public boolean onBlockActivated(
        World world, BlockPos pos, IBlockState state,
        EntityPlayer player, EnumHand hand, // ItemStack heldItem,
        EnumFacing side, float hitX, float hitY, float hitZ
    ) {
        if ( side != state.getValue(FACING) ) return false;
        if ( world.isRemote ) return true;

        String msg = "";

        if (hitY < 0.5f) {
            msg = this.getTE(world, pos).gonnaString();
        } else {
            msg = this.getTE(world, pos).neverString();
        }
        player.sendMessage(new TextComponentString(msg));

        return true;
    }

    // === the following appear to be garbage boilerplate things ===

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        // Since we only allow horizontal rotation we need only 2 bits for facing. North, South, West, East start at index 2 so we have to add 2 here.
        return getDefaultState().withProperty(FACING, EnumFacing.getFront((meta & 3) + 2));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        // Since we only allow horizontal rotation we need only 2 bits for facing. North, South, West, East start at index 2 so we have to subtract 2 here.
        return state.getValue(FACING).getIndex()-2;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
}