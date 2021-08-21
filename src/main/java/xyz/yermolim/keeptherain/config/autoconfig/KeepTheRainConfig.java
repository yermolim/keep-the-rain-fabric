package xyz.yermolim.keeptherain.config.autoconfig;

import xyz.yermolim.keeptherain.KeepTheRain;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = KeepTheRain.MOD_ID)
public class KeepTheRainConfig implements ConfigData {  
  
  @ConfigEntry.Category("RainChance")
  @ConfigEntry.Gui.CollapsibleObject
  public RainChanceConfig RainChance = new RainChanceConfig();
}
