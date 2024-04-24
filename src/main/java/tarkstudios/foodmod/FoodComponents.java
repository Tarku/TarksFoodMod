package tarkstudios.foodmod;

import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoodComponents {
    public static Logger LOGGER = LoggerFactory.getLogger("tarksfoodmod/food-components");

    public static FoodComponent TOUGH_MEAT;
    public static FoodComponent TURTLE_MEAT;
    public static FoodComponent COOKED_TOUGH_MEAT;
    public static FoodComponent COOKED_TURTLE_MEAT;
    public static FoodComponent FRIED_EGG;
    public static FoodComponent HUNTERS_STEW;
    public static FoodComponent PICKLED_SEAWEED;
    public static FoodComponent MUTINEERS_CARPACCIO;

    public static void initialize()
    {
        TOUGH_MEAT = (new FoodComponent.Builder())
            .hunger(1)
            .meat()
                .saturationModifier(0.2F)
                .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 350), 0.3F)
                .build();

        TURTLE_MEAT = (new FoodComponent.Builder())
                .hunger(1)
                .meat()
                .saturationModifier(0.2F)
                .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 1), 0.3F)
                .build();

        COOKED_TOUGH_MEAT = (new FoodComponent.Builder())
                .hunger(5)
                .meat()
                .saturationModifier(0.6F)
                .build();

        COOKED_TURTLE_MEAT = (new FoodComponent.Builder())
                .hunger(6)
                .meat()
                .saturationModifier(0.6F)
                .build();

        FRIED_EGG = (new FoodComponent.Builder())
                .hunger(7)
                .saturationModifier(0.9F)
                .build();

        HUNTERS_STEW = (new FoodComponent.Builder())
                .hunger(8)
                .saturationModifier(1.2F)
                .build();

        PICKLED_SEAWEED = (new FoodComponent.Builder())
                .hunger(4)
                .saturationModifier(0.1F)
                .build();

        MUTINEERS_CARPACCIO = (new FoodComponent.Builder())
                .hunger(6)
                .saturationModifier(1.1F)
                .statusEffect(new StatusEffectInstance(TarksFoodMod.FISHING_BOOST_EFFECT, 2400), 3.0f)
                .build();

        LOGGER.info("Initialized food components");
    }

}
