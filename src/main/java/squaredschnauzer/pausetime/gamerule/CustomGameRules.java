package squaredschnauzer.pausetime.gamerule;

import net.minecraft.core.data.gamerule.GameRuleBoolean;
import net.minecraft.core.data.gamerule.GameRules;

public class CustomGameRules {
	public static GameRuleBoolean FREEZE_TIME_WHEN_EMPTY = new GameRuleBoolean("freezeTimeWhenEmpty", true);

	public static void register() {
		GameRules.register(FREEZE_TIME_WHEN_EMPTY);
	}
}
