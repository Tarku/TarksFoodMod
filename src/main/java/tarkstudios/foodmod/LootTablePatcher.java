package tarkstudios.foodmod;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LootTablePatcher {
    private final static EntityType<?>[] EntitiesDroppingToughMeat = new EntityType<?>[]
    {
        EntityType.POLAR_BEAR,
            EntityType.WOLF,
            EntityType.RAVAGER,
            EntityType.FOX,
            EntityType.SNIFFER,
            EntityType.STRIDER
    };

    public static void initialize()
    {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {

            for (var entity : EntitiesDroppingToughMeat)
            {
                Identifier entityLootTableId = entity.getLootTableId();

                if (source.isBuiltin() && id.equals(entityLootTableId))
                {
                    LootPool.Builder poolBuilder = LootPool.builder()
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                        .with(ItemEntry.builder(FoodItems.TOUGH_MEAT));

                    tableBuilder.pool(poolBuilder.build());
                }
            }

            Identifier turtleLootTableId = EntityType.TURTLE.getLootTableId();

            if (source.isBuiltin() && id.equals(turtleLootTableId))
            {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                        .with(ItemEntry.builder(FoodItems.TURTLE_MEAT));

                tableBuilder.pool(poolBuilder.build());
            }
        }
        ));
    }
}
