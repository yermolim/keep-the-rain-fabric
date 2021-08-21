package xyz.yermolim.keeptherain.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;

/**
 * Callback on world waking from sleep.
 * Called before the players are woken.
 * Upon return:
 * - SUCCESS cancels further processing and continues with normal behavior
 * - PASS falls back to further processing and defaults to SUCCESS if no other listeners are available
 * - FAIL cancels further processing
/**
 */
public interface WorldWakeCallback {  
  Event<WorldWakeCallback> WORLD_WAKE = EventFactory.createArrayBacked(WorldWakeCallback.class, 
    (listeners) -> (world) -> {
      for (WorldWakeCallback listener : listeners) {
          ActionResult result = listener.runAction(world);
          if (result != ActionResult.PASS) {
              return result;
          }
      }
      return ActionResult.PASS;
    });

  ActionResult runAction(ServerWorld world);
}