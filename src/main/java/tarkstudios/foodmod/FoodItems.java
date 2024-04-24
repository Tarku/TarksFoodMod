package tarkstudios.foodmod;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class FoodItems
{
    public enum EatingReturns
    {
        NOTHING,
        BOTTLE,
        BOWL
    }

    public static Logger LOGGER = LoggerFactory.getLogger("tarksfoodmod/items");

    public static Item TOUGH_MEAT;
    public static Item TURTLE_MEAT;
    public static Item COOKED_TOUGH_MEAT;
    public static Item COOKED_TURTLE_MEAT;
    public static Item FRIED_EGG;
    public static Item HUNTERS_STEW;

    public static Item PICKLED_SEAWEED;

    public static Item MUTINEERS_CARPACCIO;


    private static Item LastItem = Items.COOKED_BEEF;

    private static void addInFoodTab(Item item)
    {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> content.addAfter(LastItem, item));

        LastItem = item;
    }

    private static void registerItem(String id, Item item)
    {
        Registry.register(
                Registries.ITEM,
                new Identifier(
                        TarksFoodMod.MOD_ID,
                        id
                ),
                item
        );
    }

    private static Item makeFoodItem(String id, FoodComponent foodComponent, EatingReturns eatingReturns)
    {
        FoodTooltipItem newItem = new FoodTooltipItem(id, foodComponent, eatingReturns);

        registerItem(id, newItem);
        addInFoodTab(newItem);

        return newItem;
    }

    public static void initialize()
    {
        TOUGH_MEAT = makeFoodItem("tough_meat", FoodComponents.TOUGH_MEAT, EatingReturns.NOTHING);
        TURTLE_MEAT = makeFoodItem("turtle_meat", FoodComponents.TURTLE_MEAT, EatingReturns.NOTHING);
        COOKED_TOUGH_MEAT = makeFoodItem("cooked_tough_meat", FoodComponents.COOKED_TOUGH_MEAT, EatingReturns.NOTHING);
        COOKED_TURTLE_MEAT = makeFoodItem("cooked_turtle_meat", FoodComponents.COOKED_TURTLE_MEAT, EatingReturns.NOTHING);
        FRIED_EGG = makeFoodItem("fried_egg", FoodComponents.FRIED_EGG, EatingReturns.NOTHING);

        HUNTERS_STEW = makeFoodItem("hunters_stew", FoodComponents.HUNTERS_STEW, EatingReturns.BOWL);

        PICKLED_SEAWEED = makeFoodItem("pickled_seaweed", FoodComponents.PICKLED_SEAWEED, EatingReturns.BOTTLE);

        MUTINEERS_CARPACCIO = makeFoodItem("mutineers_carpaccio", FoodComponents.MUTINEERS_CARPACCIO, EatingReturns.BOWL);

        LOGGER.info("Initialized food items");
    }
}
