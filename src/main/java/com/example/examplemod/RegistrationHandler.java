package com.example.examplemod;

import com.example.examplemod.blocks.FasphaltBlock;
import com.example.examplemod.blocks.FasphaltBlockDirectional;
import com.example.examplemod.blocks.HydraBlock;
import com.example.examplemod.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ExampleMod.MODID)
public class RegistrationHandler {
    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        final Item[] items = {
            setItemName("first_item")
        };

        final Item[] itemBlocks = {
            new ItemBlock(ModBlocks.FIRST_BLOCK)
                .setRegistryName(ModBlocks.FIRST_BLOCK.getRegistryName()),
            new ItemBlock(ModBlocks.FASPHALT_BLOCK)
                .setRegistryName(ModBlocks.FASPHALT_BLOCK.getRegistryName()),
            new ItemBlock(ModBlocks.MOBSAND_BLOCK)
                .setRegistryName(ModBlocks.MOBSAND_BLOCK.getRegistryName()),
            new ItemBlock(ModBlocks.FASPHALT_BLOCK_DIRECTIONAL)
                .setRegistryName(ModBlocks.FASPHALT_BLOCK_DIRECTIONAL.getRegistryName())    
        };

        event.getRegistry().registerAll(items);
        event.getRegistry().registerAll(itemBlocks);
    }

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        final Block[] blocks = {
            setBlockName("first_block", new HydraBlock()),
            setBlockName("fasphalt_block", new FasphaltBlock(1.4, 1)),
            setBlockName("mobsand_block", new FasphaltBlock(1, 0.03)),
            setBlockName("fasphalt_block_directional", new FasphaltBlockDirectional(1.4, 1))
        };

        event.getRegistry().registerAll(blocks);
    }

    public static Item setItemName(String name) {
        return new Item()
            .setRegistryName(ExampleMod.MODID, "first_item")
            .setTranslationKey(ExampleMod.MODID + "." + "first_item")
            .setCreativeTab(CreativeTabs.MISC);
    }

    public static Block setBlockName (String name, Block block) {
        return block
            .setRegistryName(ExampleMod.MODID, name)
            .setTranslationKey(ExampleMod.MODID + "." + name)
            .setCreativeTab(CreativeTabs.MISC);
    }
}
