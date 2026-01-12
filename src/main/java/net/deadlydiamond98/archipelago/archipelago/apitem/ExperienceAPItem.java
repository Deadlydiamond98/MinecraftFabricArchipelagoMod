package net.deadlydiamond98.archipelago.archipelago.apitem;

import net.minecraft.server.network.ServerPlayerEntity;

public class ExperienceAPItem extends AbstractAPItem {
    private final int experience;
    private final int levels;

    public ExperienceAPItem(int experience, int levels) {
        this.experience = experience;
        this.levels = levels;
    }

    @Override
    public void applyReward(ServerPlayerEntity player) {
        player.addExperience(this.experience);
        player.addExperienceLevels(this.levels);
    }
}
