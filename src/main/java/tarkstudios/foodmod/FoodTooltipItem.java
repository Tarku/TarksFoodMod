package tarkstudios.foodmod;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class FoodTooltipItem extends Item {
    private final String id;
    private final FoodItems.EatingReturns eatingReturns;

    public FoodTooltipItem(String id, FoodComponent foodComponent, FoodItems.EatingReturns eatingReturns)
    {
        super(new Settings().food(foodComponent).maxCount(eatingReturns != FoodItems.EatingReturns.NOTHING ? 1 : 64));


        this.id = id;
        this.eatingReturns = eatingReturns;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
    {
        String tooltipId = String.format("item.%s.%s.tooltip", TarksFoodMod.MOD_ID, id);
        tooltip.add(Text.translatable(tooltipId).formatted(Formatting.DARK_PURPLE).formatted(Formatting.ITALIC));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);

        Item returnsItem = Items.AIR;

        switch (eatingReturns)
        {
            case NOTHING -> {
                return user.eatFood(world, stack);
            }
            case BOWL -> returnsItem = Items.BOWL;
            case BOTTLE -> returnsItem = Items.GLASS_BOTTLE;
        }

        return user instanceof PlayerEntity && ((PlayerEntity)user).getAbilities().creativeMode ? itemStack : new ItemStack(returnsItem);
    }
}
