package com.example.examplemod.blocks;



import com.example.examplemod.ExampleMod;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.MovementInput;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;


@EventBusSubscriber(value = Side.CLIENT, modid = ExampleMod.MODID)
public class FasphaltBlockDirectional extends BlockHorizontal {

	private double amountPlayer;
	private double amountMob;
	private Logger logger = org.apache.logging.log4j.LogManager.getLogger();


	public FasphaltBlockDirectional(double amountPlayer, double amountMob) {
		super(Material.ROCK);
		this.setHardness(3F);
		this.setResistance(20F);
		this.setSoundType(SoundType.STONE);
		//If you move faster, it takes longer to top. Friction should be adjust to prevent "ice like" sliding.
		if ( amountPlayer >= 1 && amountMob >= 1 ) {
			this.setDefaultSlipperiness(0.5f);
		}
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

		this.amountPlayer = amountPlayer;
		this.amountMob = amountMob;
	}

	@SideOnly(Side.CLIENT)
	private static MovementInput manualInputCheck;

	/**
     * When an entity walks on the block, there speed is updated depending on the block values.
	 * If the block is under water, different values are applied. Different values are used depending
	 * if the entity is a player or a MOB
     * void
     */
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


	/**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
				logger.info(placer.getHorizontalFacing().toString());
				
				return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
				

	}


	 /**
     * Get the integer meta value repersenting the facing direction for meta data. 0-3
     * int
     */
	@Override
	public int getMetaFromState(IBlockState state) {

		int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
        return i;
	}

	/**
     * Get the BlockState updated with EnumFacing based on meta value
     * IBlockState
     */

	@Override
	public IBlockState getStateFromMeta(int meta) {

		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
		
		
	}

	/**
     * A little confused on the need for this. From reading, BlockHorizonal already has a FACING property,
	 * not sure why we need to add it manually. Should read the the BlockHorizontal source.
     * BlockStateContainer
     */	

	@Override
	protected BlockStateContainer createBlockState() {

		return new BlockStateContainer(this, new IProperty[] {FACING});
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (facing == EnumFacing.DOWN){
			logger.info("That's my no no spot");
		} else {
			logger.info("Tee Hee, that tickles");
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
}
