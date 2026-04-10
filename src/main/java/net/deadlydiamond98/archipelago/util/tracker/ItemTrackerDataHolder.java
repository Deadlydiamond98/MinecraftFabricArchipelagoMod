package net.deadlydiamond98.archipelago.util.tracker;

import net.minecraft.text.Text;

import java.text.DecimalFormat;
import java.util.List;

public record ItemTrackerDataHolder(int goal, int currentAdvancements, int totalAdvancements, int currentItems, int totalItems, int currentRubies, int totalRubies, List<TrackerEntry> entries) implements IAbilityCheck {
    public Text advancements() {
        return getAmount(this.currentAdvancements, this.totalAdvancements);
    }

    public Text items() {
        return getAmount(this.currentItems, this.totalItems);
    }

    public Text rubies() {
        return getAmount(this.currentRubies, this.totalRubies);
    }

    public Text getAmount(int current, int max) {
        if (max <= 0) {
            return Text.translatable("gui.archipelago.none_present");
        }

        double percentage = current / (double) max;

        if (current >= max) {
            percentage = 1;
        }

        DecimalFormat decimalFormat = new DecimalFormat(".0%");
        String percentageStr = decimalFormat.format(percentage);
        current = Math.min(current, max);

        if (percentage <= 0) {
            percentageStr = "0.0%";
        }
        return Text.literal((current < 10 ? "0" : "") + current + " / " + (max < 10 ? "0" : "") + max + " (" + percentageStr + ")");
    }

    public record TrackerEntry(int count, String name, String id, boolean isProgressive) {}

    @Override
    public int getIntCheckValue(String id) {
        for (TrackerEntry entry : entries) {
            if (entry.id.equals(id)) {
                return entry.count;
            }
        }
        return 0;
    }

    @Override
    public boolean getBooleanCheckValue(String id) {
        for (TrackerEntry entry : entries) {
            if (entry.id.equals(id)) {
                return entry.count > 0;
            }
        }
        return false;
    }
}
