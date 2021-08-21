package xyz.yermolim.keeptherain;

import xyz.yermolim.keeptherain.config.autoconfig.KeepTheRainConfig;
import xyz.yermolim.keeptherain.core.RainManager;
import xyz.yermolim.keeptherain.events.WorldWakeCallback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.ActionResult;
import net.minecraft.world.level.ServerWorldProperties;

public class KeepTheRain implements ModInitializer {
	public static final String MOD_ID = "keeptherain";
	public static final String MOD_NAME = "Keep the rain!";
	public static final String MOD_VERSION = "%gradle.version%";
	
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	
	public static KeepTheRainConfig CONFIG;
	
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		
		AutoConfig.register(KeepTheRainConfig.class, JanksonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(KeepTheRainConfig.class).getConfig();

		WorldWakeCallback.WORLD_WAKE.register((world) -> {
			var levelProps = world.getLevelProperties();
			if (!(levelProps instanceof ServerWorldProperties)) {
				return ActionResult.PASS;
			}    
			var serverWorldInfo = (ServerWorldProperties)levelProps;
			RainManager.onWake(serverWorldInfo, 
				Double.valueOf(CONFIG.RainChance.rainContinuationChance) / 100d, 
				Double.valueOf(CONFIG.RainChance.thunderContinuationChance) / 100d,
				CONFIG.RainChance.preserveRainTime, 
				(String text) -> { LOGGER.info(text); });
			
			return ActionResult.SUCCESS;
		});
	}
}
