package com.example.examplemod;

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
                .setRegistryName(ModBlocks.FIRST_BLOCK.getRegistryName())
                // .setRegistryName(ExampleMod.MODID, "first_block")
        };

        event.getRegistry().registerAll(items);
        event.getRegistry().registerAll(itemBlocks);
    }

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        final Block[] blocks = {
            setBlockName("first_block")
        };

        event.getRegistry().registerAll(blocks);
    }

    public static Item setItemName(String name) {
        return new Item()
            .setRegistryName(ExampleMod.MODID, "first_item")
            .setTranslationKey(ExampleMod.MODID + "." + "first_item")
            .setCreativeTab(CreativeTabs.MISC);
    }

    public static Block setBlockName (String name) {
        return new Block(Material.ROCK)
            .setRegistryName(ExampleMod.MODID, name)
            .setTranslationKey(ExampleMod.MODID + "." + name)
            .setCreativeTab(CreativeTabs.MISC);
    }
}
