package net.deadlydiamond98.archipelago.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.World;

public class TotemOfMeteorologyItem extends EssenceOfArchipelagoItem {
    public TotemOfMeteorologyItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld server) {
            SoundEvent sound = SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
            if (world.isThundering()) {
                server.setWeather(getDuration(world, ServerWorld.CLEAR_WEATHER_DURATION_PROVIDER), 0, false, false);
                world.playSound(null, user.getBlockPos(), sound, SoundCategory.PLAYERS, 1, 2);
                user.sendMessage(weatherLang("clear"), true);
            } else if (world.isRaining()) {
                server.setWeather(0, getDuration(world, ServerWorld.THUNDER_WEATHER_DURATION_PROVIDER), true, true);
                world.playSound(null, user.getBlockPos(), sound, SoundCategory.PLAYERS, 1, 1.25f);
                user.sendMessage(weatherLang("thunder"), true);
            } else {
                server.setWeather(0, getDuration(world, ServerWorld.RAIN_WEATHER_DURATION_PROVIDER), true, false);
                user.sendMessage(weatherLang("rain"), true);
            }
            world.playSound(null, user.getBlockPos(), sound, SoundCategory.PLAYERS, 1, 1);
        }
        user.getItemCooldownManager().set(this, 120); // Cooldown until the Weather Gradient kicks in
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    private int getDuration(World world, IntProvider provider) {
        return provider.get(world.getRandom());
    }

    private Text weatherLang(String str) {
        return Text.translatable("commands.weather.set." + str);
    }
}
