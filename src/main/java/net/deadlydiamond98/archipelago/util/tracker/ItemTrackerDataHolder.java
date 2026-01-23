package net.deadlydiamond98.archipelago.util.tracker;

import net.minecraft.text.Text;

import java.text.DecimalFormat;
import java.util.List;

public record ItemTrackerDataHolder(int goal, int currentAdvancements, int totalAdvancements, int currentRubies, int totalRubies, List<TrackerEntry> entries) {
    public Text advancements() {
        return getAmount(this.currentAdvancements, this.totalAdvancements);
    }

    public Text rubies() {
        return getAmount(this.currentRubies, this.totalRubies);
    }

    public Text getAmount(int current, int max) {
        if (max <= 0) {
            return Text.translatable("gui.archipelago.none_present");
        }

        double percentage = current / (double) max;
        percentage = percentage >= 100 ? 100 : percentage;

        DecimalFormat decimalFormat = new DecimalFormat(".0%");
        String percentageStr = decimalFormat.format(percentage);
        current = Math.min(current, max);

        return Text.literal((current < 10 ? "0" : "") + current + " / " + (max < 10 ? "0" : "") + max + " (" + percentageStr + "%)");
    }

    public record TrackerEntry(int count, String name, String id, boolean isProgressive) {}
}
