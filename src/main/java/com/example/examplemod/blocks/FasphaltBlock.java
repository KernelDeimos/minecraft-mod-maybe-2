package com.example.examplemod.blocks;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber(value = Side.CLIENT, modid = ExampleMod.MODID)
public class FasphaltBlock extends Block {

    
	public FasphaltBlock() {
		super(Material.ROCK);
		
	}

    private static Logger logger;

	@SideOnly(Side.CLIENT)
	private static MovementInput manualInputCheck;

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void speedupPlayer(PlayerTickEvent event) {
		logger.info("I am triggering the event");
        if (event.phase == TickEvent.Phase.START && event.side.isClient() && event.player.onGround && event.player instanceof EntityPlayerSP) {
			if (manualInputCheck == null) {
				manualInputCheck = new MovementInputFromOptions(Minecraft.getMinecraft().gameSettings);
			}


			EntityPlayerSP player = (EntityPlayerSP) event.player;
			IBlockState blk = player.world.getBlockState(player.getPosition().down());
			Block below = blk.getBlock();
            if (below == this) {
				manualInputCheck.updatePlayerMoveState();
				if (manualInputCheck.moveForward != 0 || manualInputCheck.moveStrafe != 0) {
					player.motionX *= 3;
					player.motionZ *= 3;
                    
				}
			}
		}
	}
}