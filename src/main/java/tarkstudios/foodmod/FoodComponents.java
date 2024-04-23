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

    public static void initialize()
    {
        TOUGH_MEAT = (new FoodComponent.Builder())
            .hunger(1)
            .meat()
                .saturationModifier(0.2F)
                .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6), 0.2F)
                .build();

        TURTLE_MEAT = (new FoodComponent.Builder())
                .hunger(1)
                .meat()
                .saturationModifier(0.2F)
                .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 6, 2), 0.3F)
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

        LOGGER.info("Initialized food components");
    }

}
