package tarkstudios.foodmod;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TarksFoodMod implements ModInitializer {
	public static final String MOD_ID = "tarksfoodmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static FishingBoostEffect FISHING_BOOST_EFFECT = new FishingBoostEffect();

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing!");

		FoodComponents.initialize();
		IngredientItems.initialize();
		FoodItems.initialize();
		LootTablePatcher.initialize();

		Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "fishing_boost"), FISHING_BOOST_EFFECT);
	}
}