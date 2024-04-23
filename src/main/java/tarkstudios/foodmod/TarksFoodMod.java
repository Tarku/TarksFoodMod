package tarkstudios.foodmod;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TarksFoodMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("tarks-food-mod");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		FoodComponents.initialize();
		FoodItems.initialize();
		LootTablePatcher.initialize();
	}
}