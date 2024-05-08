package squaredschnauzer.pausetime;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squaredschnauzer.pausetime.gamerule.CustomGameRules;

public class PauseTime implements ModInitializer {
	public static final String MOD_ID = "pausetime";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("[PauseTimeMod] Time on this server will be paused when there are no players.");
		CustomGameRules.register();
	}
}
