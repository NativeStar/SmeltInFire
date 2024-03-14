package com.suisho.mcmod.smeltinfire.release.smeltinfire;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Random;

public class SmeltInFire implements ModInitializer {
    private static final Random random = new Random();
    @Override
    public void onInitialize() {

    }

    public static Item getVanillaOre(String id) {
        switch (id) {
            case "coal_ore":
            case "deepslate_coal_ore":
                return Items.COAL;
            case "iron_ore":
            case "deepslate_iron_ore":
            case "raw_iron":
                return Items.IRON_INGOT;
            case "gold_ore":
            case "deepslate_gold_ore":
            case "nether_gold_ore":
            case "raw_gold":
                return Items.GOLD_INGOT;
            case "copper_ore":
            case "deepslate_copper_ore":
            case "raw_copper":
                return Items.COPPER_INGOT;
            case "redstone_ore":
            case "deepslate_redstone_ore":
                return Items.REDSTONE;
            case "emerald_ore":
            case "deepslate_emerald_ore":
                return Items.EMERALD;
            case "lapis_ore":
            case "deepslate_lapis_ore":
                return Items.LAPIS_LAZULI;
            case "diamond_ore":
            case "deepslate_diamond_ore":
                return Items.DIAMOND;
            case "nether_quartz_ore":
                return Items.QUARTZ;
            case "ancient_debris":
                return Items.NETHERITE_SCRAP;
            case "raw_iron_block":
                return Items.IRON_BLOCK;
            case "raw_gold_block":
                return Items.GOLD_BLOCK;
            case "raw_copper_block":
                return Items.COPPER_BLOCK;
            default:
                return null;
        }
    }
    public static boolean hasFireImmune(String id){
        switch (id){
            case "iron_ingot":
            case "gold_ingot":
            case "coal":
            case "copper_ingot":
            case "redstone":
            case "emerald":
            case "lapis_lazuli":
            case "diamond":
            case "quartz":
            case "netherite_scrap":
            case "iron_block":
            case "gold_block":
            case "copper_block":
                return true;
        }
        return false;
    }

    public static double itemSpawnVelocity(){
        return random.nextBoolean() ? 0.12d : -0.12d;
    }
}
