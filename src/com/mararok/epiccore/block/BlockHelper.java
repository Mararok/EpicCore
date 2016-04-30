/**
 * EpicCore
 * The MIT License
 * Copyright (C) 2016 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.block;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * Mics of base block manipulation functions
 */
public class BlockHelper {

  public static void setWoolBlock(Block block, DyeColor dye) {
    block.setType(Material.WOOL);
    setBlockColor(block, dye);
  }

  public static void setStainedGlassBlock(Block block, DyeColor dye) {
    block.setType(Material.STAINED_GLASS);
    setBlockColor(block, dye);
  }

  public static void setStainedGlassPaneBlock(Block block, DyeColor dye) {
    block.setType(Material.STAINED_GLASS_PANE);
    setBlockColor(block, dye);
  }

  public static void setStainedClayBlock(Block block, DyeColor dye) {
    block.setType(Material.STAINED_CLAY);
    setBlockColor(block, dye);
  }

  @SuppressWarnings("deprecation")
  public static void setBlockColor(Block block, DyeColor dye) {
    block.setData(dye.getData());
  }

  @SuppressWarnings("deprecation")
  public static DyeColor getBlockColor(Block block) {
    return DyeColor.getByData(block.getData());
  }
}
