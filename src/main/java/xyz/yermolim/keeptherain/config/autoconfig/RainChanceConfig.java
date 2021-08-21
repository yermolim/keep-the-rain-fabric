package xyz.yermolim.keeptherain.config.autoconfig;

import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

public class RainChanceConfig { 
   
  @Comment(value = "\nEnable preserving the time left to next rain after players woke up")
  public boolean preserveRainTime = true;

  @Comment(value = "\nChange the chance of rain continuation after players woke up from 0(never) to 100(always)")
  @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
  public int rainContinuationChance = 50;

  @Comment(value = "\nChange the chance of thunder continuation after players woke up from 0(never) to 1(always when rain continuation is passed)")
  @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
  public int thunderContinuationChance = 50;
}
