package com.example.examplemod.blocks;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@EventBusSubscriber(value = Side.CLIENT, modid = ExampleMod.MODID)
public class FasphaltBlockDirectional extends BlockHorizontal {

	private double amountPlayer;
	private double amountMob;

	public FasphaltBlockDirectional(double amountPlayer, double amountMob) {
		super(Material.ROCK);
		this.setHardness(3F);
		this.setResistance(20F);
		this.setSoundType(SoundType.STONE);
		if ( amountPlayer >= 1 && amountMob >= 1 ) {
			this.setDefaultSlipperiness(0.5f);
		}
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

		this.amountPlayer = amountPlayer;
		this.amountMob = amountMob;
	}

	@SideOnly(Side.CLIENT)
	private static MovementInput manualInputCheck;

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		double amount = (entity instanceof EntityPlayer)
			? this.amountPlayer : this.amountMob;

		if ( entity.isInWater() || entity.isInLava() )
			amount = this.waterAmount(amount);
		
		if (amount != 1) {
			entity.motionX *= amount;
			entity.motionZ *= amount;
		}
	}

	// Reduces the intensity of a factor, whether positive or negative
	private double waterAmount(double amount) {
		return 0.5 * (amount - 1) + 1;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		// TODO Auto-generated method stub
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		// TODO Auto-generated method stub
		return getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		// TODO Auto-generated method stub
		return new BlockStateContainer(this, FACING);
	}
}
