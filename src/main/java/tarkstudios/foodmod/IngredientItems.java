package tarkstudios.foodmod;


import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class IngredientItems {

    public static Item SALT;

    public static void initialize()
    {
        SALT = new Item(new Item.Settings());

        Registry.register(Registries.ITEM, new Identifier(TarksFoodMod.MOD_ID, "salt"), SALT);



    }

}
