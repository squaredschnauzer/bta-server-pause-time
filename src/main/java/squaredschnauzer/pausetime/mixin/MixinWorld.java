package squaredschnauzer.pausetime.mixin;

import net.minecraft.core.data.gamerule.GameRule;
import net.minecraft.core.data.gamerule.GameRules;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import net.minecraft.core.world.save.LevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import squaredschnauzer.pausetime.gamerule.CustomGameRules;

import java.util.List;

@Mixin(value = World.class, remap = false)
public class MixinWorld {
	@Shadow
	public List<EntityPlayer> players;

	@Shadow
	protected LevelData levelData;

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/save/LevelData;setWorldTime(J)V", shift = At.Shift.BEFORE), cancellable = true)
	private void onTick(CallbackInfo ci) {
		MixinWorld world = this;
		boolean doDayCycle = this.getGameRuleValue(GameRules.DO_DAY_CYCLE);

		if (doDayCycle) {
			boolean freezeTimeWhenEmpty = world.getGameRuleValue(CustomGameRules.FREEZE_TIME_WHEN_EMPTY);
			boolean playersPresent = !this.players.isEmpty();

			if (playersPresent || !freezeTimeWhenEmpty) {
				this.levelData.setWorldTime(this.levelData.getWorldTime() + 1L);
			} else {
				ci.cancel();
			}
		}
	}

	@Shadow
	public <T> T getGameRuleValue(GameRule<T> gameRule) {
		return null;
	}
}
