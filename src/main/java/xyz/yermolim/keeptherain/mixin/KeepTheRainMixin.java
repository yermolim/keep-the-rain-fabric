package xyz.yermolim.keeptherain.mixin;
import xyz.yermolim.keeptherain.events.WorldWakeCallback;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;

import java.util.function.BooleanSupplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class KeepTheRainMixin {

	@Inject(at = @At(value = "INVOKE", target = "net/minecraft/server/world/ServerWorld.setTimeOfDay(J)V"), method = "tick")
	private void onWorldWake(BooleanSupplier shouldKeepTicking, CallbackInfo info) {
		ServerWorld world = (ServerWorld) (Object) this;
		ActionResult result = WorldWakeCallback.WORLD_WAKE.invoker().runAction(world);		 
		if (result == ActionResult.FAIL) {
			info.cancel();
		}
	}
}
