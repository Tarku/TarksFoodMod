package tarkstudios.foodmod.mixin;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import tarkstudios.foodmod.TarksFoodMod;

@Mixin(FishingRodItem.class)
public class FishingRodMixin {

	@Overwrite
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {


		ItemStack itemStack = user.getStackInHand(hand);
		int i;
		if (user.fishHook != null) {
			if (!world.isClient) {
				i = user.fishHook.use(itemStack);
				itemStack.damage(i, user, p -> p.sendToolBreakStatus(hand));
			}

			world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
			user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
		} else {
			world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
			if (!world.isClient) {
				int fishingBoostEffectBonus = 0;
				if (user.hasStatusEffect(TarksFoodMod.FISHING_BOOST_EFFECT))
				{
					StatusEffectInstance instance = user.getStatusEffect(TarksFoodMod.FISHING_BOOST_EFFECT);

					if (instance != null)
					{
						fishingBoostEffectBonus = instance.getAmplifier();
					}
				}

				int bonus = fishingBoostEffectBonus != 0 ? 1 + fishingBoostEffectBonus : 0;

				i = EnchantmentHelper.getLure(itemStack);
				int j = EnchantmentHelper.getLuckOfTheSea(itemStack);
				world.spawnEntity(new FishingBobberEntity(user, world, j, i + bonus));
			}

			user.incrementStat(Stats.USED.getOrCreateStat(Items.FISHING_ROD));
			user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
		}

		return TypedActionResult.success(itemStack, world.isClient());
	}
}