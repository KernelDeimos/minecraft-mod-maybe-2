package com.example.examplemod.blocks;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@EventBusSubscriber(value = Side.CLIENT, modid = ExampleMod.MODID)
public class FasphaltBlock extends Block {

    
    
	public FasphaltBlock() {
		super(Material.ROCK);
		this.setHardness(3F);
		this.setResistance(20F);
		this.setSoundType(SoundType.STONE);
        this.setDefaultSlipperiness(0.5f);
	}

	@SideOnly(Side.CLIENT)
	private static MovementInput manualInputCheck;

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if (entity instanceof  EntityPlayer){
            double amount = (entity.isInWater() || entity.isInLava())
                ? 1.2 : 1.4;
            entity.motionX *= amount;
            entity.motionY *= amount;
        }
	}
}