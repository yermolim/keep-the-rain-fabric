package xyz.yermolim.keeptherain.core;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import net.minecraft.world.level.ServerWorldProperties;

public class RainManager {

  public static void onWake(ServerWorldProperties serverWorldInfo, double rainContinuationChance,
  double thunderContinuationChance, boolean preserveRainTime, Consumer<String> logger) {
    int untilRain = !serverWorldInfo.isRaining() ? serverWorldInfo.getRainTime() : 0;
    int untilThunder = !serverWorldInfo.isThundering() ? serverWorldInfo.getThunderTime() : 0;

    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        if (untilRain == 0) {
          double rainRoll = Math.random();
          logger.accept(String.format("Rain continuation dice rolled: %s/%s.", rainRoll, rainContinuationChance));
          if (rainRoll < rainContinuationChance) {
            serverWorldInfo.setRaining(true);
            logger.accept("Rain continuation passed.");
            if (untilThunder == 0) {
              double thunderRoll = Math.random();
              logger.accept(String.format("Thunder continuation dice rolled: %s/%s.", thunderRoll, thunderContinuationChance));
              if (thunderRoll < thunderContinuationChance) {
                serverWorldInfo.setThundering(true);
                logger.accept("Thunder continuation passed.");
              } else {
                logger.accept("Thunder continuation not passed.");
              }
            }
          } else {
            serverWorldInfo.setRaining(false);
            serverWorldInfo.setThundering(false);
            logger.accept("Rain continuation not passed.");
          }
        } else if (preserveRainTime) {
          serverWorldInfo.setRainTime(untilRain);
          serverWorldInfo.setThunderTime(untilThunder);
        }
      }
    }, 10);
  }
}
