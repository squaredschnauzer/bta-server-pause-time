package squaredschnauzer.pausetime.mixin;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import net.minecraft.core.world.save.LevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import squaredschnauzer.pausetime.gamerule.CustomGameRules;

import java.util.List;

@Mixin(value = World.class, remap = false)
public class MixinWorld {
	@Shadow
	public List<EntityPlayer> players;

	@Shadow
	protected LevelData levelData;

	@ModifyVariable(method = "tick", at = @At(value = "LOAD", ordinal = 0), ordinal = 0)
	private long modifyWorldTime(long l1) {
		if (!this.players.isEmpty() || !this.levelData.getGameRules().getValue(CustomGameRules.FREEZE_TIME_WHEN_EMPTY)) {
			return l1;
		}

		return this.levelData.getWorldTime();
	}
}
