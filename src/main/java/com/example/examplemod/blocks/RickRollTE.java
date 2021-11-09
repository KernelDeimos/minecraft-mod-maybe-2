package com.example.examplemod.blocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class RickRollTE extends TileEntity {
    private int neverCount = 0;
    private int gonnaCount = 0;

    private static String[] neverStrings = {
        "never", "give", "up", "gonna", "you", "never", "run", "and", "you",
    };

    private static String[] gonnaStrings = {
        "gonna", "you", "never", "let", "down", "gonna", "around", "desert",
    };

    public String neverString() {
        int tmp = neverCount;
        neverCount = (neverCount + 1) % RickRollTE.neverStrings.length;
        return neverStrings[tmp];
    }

    public String gonnaString() {
        int tmp = gonnaCount;
        gonnaCount = (gonnaCount + 1) % RickRollTE.gonnaStrings.length;
        return gonnaStrings[tmp];
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        neverCount = compound.getInteger("neverCount");
        gonnaCount = compound.getInteger("gonnaCount");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("neverCount", neverCount);
        compound.setInteger("gonnaCount", gonnaCount);
        return compound;
    }
}
