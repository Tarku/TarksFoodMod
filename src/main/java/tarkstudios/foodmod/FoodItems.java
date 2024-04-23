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
    public static Logger LOGGER = LoggerFactory.getLogger("tarksfoodmod/items");

    public static Item TOUGH_MEAT;
    public static Item TURTLE_MEAT;
    public static Item COOKED_TOUGH_MEAT;
    public static Item COOKED_TURTLE_MEAT;
    public static Item FRIED_EGG;

    private static Item LastItem = Items.COOKED_BEEF;

    private static void addInFoodTab(Item item)
    {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
            content.addAfter(LastItem, item);
        });

        LastItem = item;
    }

    private static void registerItem(String id, Item item)
    {
        Registry.register(Registries.ITEM, new Identifier("tarksfoodmod", id), item);
    }

    public static void initialize()
    {
        TOUGH_MEAT = new Item(new Item.Settings().food(FoodComponents.TOUGH_MEAT));
        registerItem("tough_meat", TOUGH_MEAT);
        addInFoodTab(TOUGH_MEAT);

        TURTLE_MEAT = new Item(new Item.Settings().food(FoodComponents.TURTLE_MEAT));
        registerItem("turtle_meat", TURTLE_MEAT);
        addInFoodTab(TURTLE_MEAT);

        COOKED_TOUGH_MEAT = new Item(new Item.Settings().food(FoodComponents.COOKED_TOUGH_MEAT));
        registerItem("cooked_tough_meat", COOKED_TOUGH_MEAT);
        addInFoodTab(COOKED_TOUGH_MEAT);

        COOKED_TURTLE_MEAT = new Item(new Item.Settings().food(FoodComponents.COOKED_TURTLE_MEAT));
        registerItem("cooked_turtle_meat", COOKED_TURTLE_MEAT);
        addInFoodTab(COOKED_TURTLE_MEAT);

        FRIED_EGG = new Item(new Item.Settings().food(FoodComponents.FRIED_EGG));
        registerItem("fried_egg", FRIED_EGG);
        addInFoodTab(FRIED_EGG);

        LOGGER.info("Initialized food items");
    }
}
