package com.example.examplemod.client;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.init.ModBlocks;
import com.example.examplemod.init.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = ExampleMod.MODID)
public class ModelRegistrationHandler {
    
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        registerModel(ModItems.FIRST_ITEM, 0);
        registerModel(Item.getItemFromBlock(ModBlocks.FIRST_BLOCK), 0);
        registerModel(Item.getItemFromBlock(ModBlocks.FASPHALT_BLOCK), 0);
        registerModel(Item.getItemFromBlock(ModBlocks.MOBSAND_BLOCK), 0);
        registerModel(Item.getItemFromBlock(ModBlocks.FASPHALT_BLOCK_DIRECTIONAL), 0);
    }

    private static void registerModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta,
            new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
