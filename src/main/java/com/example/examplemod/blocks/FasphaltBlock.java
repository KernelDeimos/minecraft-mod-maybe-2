package com.example.examplemod.blocks;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@EventBusSubscriber(value = Side.CLIENT, modid = ExampleMod.MODID)
public class FasphaltBlock extends Block {

	private double amountPlayer;
	private double amountMob;

	public FasphaltBlock(double amountPlayer, double amountMob) {
		super(Material.ROCK);
		this.setHardness(3F);
		this.setResistance(20F);
		this.setSoundType(SoundType.STONE);
		if ( amountPlayer >= 1 && amountMob >= 1 ) {
			this.setDefaultSlipperiness(0.5f);
		}

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
}