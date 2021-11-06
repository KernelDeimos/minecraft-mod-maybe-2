package com.example.examplemod.blocks;

import java.util.Random;

import com.example.examplemod.init.ModBlocks;
import com.example.examplemod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class HydraBlock extends Block {
    Random rand;

    public HydraBlock() {
        super(Material.ROCK);
        this.setHardness(2F);
        this.setResistance(20F);
        this.setSoundType(SoundType.METAL);

        this.rand = new Random();
        this.rand.setSeed(100);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(new ItemStack(ItemBlock.getItemFromBlock(ModBlocks.FIRST_BLOCK), 2));
        if ( this.rand.nextInt(100) < 20 ) {
            drops.add(new ItemStack(ModItems.FIRST_ITEM, 1));
        }
    }
}
