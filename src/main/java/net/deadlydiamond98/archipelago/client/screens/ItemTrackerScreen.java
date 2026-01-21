package net.deadlydiamond98.archipelago.client.screens;

import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.networking.c2s.RequestTrackerInformationC2SPacket;
import net.deadlydiamond98.archipelago.util.tracker.ItemTrackerDataHolder;
import net.deadlydiamond98.archipelago.util.tracker.TrackerScreenUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class ItemTrackerScreen extends Screen {
    public static ItemTrackerDataHolder tracker;

    private static final Identifier WINDOW_TEXTURE = APMod.id("textures/gui/tracker_gui.png");
    private static final Identifier RUBY_ICON_TEXTURE = APMod.id("textures/gui/tracker_icon/ruby.png");
    private static final Identifier TROPHY_ICON_TEXTURE = APMod.id("textures/gui/tracker_icon/trophy.png");
    private int guiX, guiY;
    protected int backgroundWidth = 256;
    protected int backgroundHeight = 229;

    private static final int ENTRY_HEIGHT = 20;
    private static final int SCROLL_STEP = 10;
    private int maxScroll;
    private int scrollOffset;

    public ItemTrackerScreen(Text title) {
        super(title);
        RequestTrackerInformationC2SPacket.send();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (tracker == null) {
            return;
        }

        this.maxScroll = Math.max(((tracker.entries().size() * ENTRY_HEIGHT) + 10) - (ENTRY_HEIGHT * 7), 0);

        this.guiX = (this.width - this.backgroundWidth) / 2;
        this.guiY = (this.height - this.backgroundHeight) / 2;
        int centerX = this.guiX + (this.backgroundWidth / 2);
        int centerY = this.guiY + (this.backgroundHeight / 2);

        this.renderBackground(context);
        context.drawTexture(WINDOW_TEXTURE, this.guiX, this.guiY, 0, 0, this.backgroundWidth, this.backgroundHeight);
        this.renderGoalProgress(context, this.guiX, this.guiY, centerX, centerY, delta);
        this.renderChecks(context, this.guiX, this.guiY);
        this.renderGoalConditionText(context, centerX, this.guiY);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (canScrollMouse(mouseX, mouseY)) {
            if (amount < 0) {
                this.scrollOffset = Math.min(this.scrollOffset + SCROLL_STEP, this.maxScroll);
            } else if (amount > 0) {
                this.scrollOffset = Math.max(this.scrollOffset - SCROLL_STEP, 0);
            }
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    private boolean canScrollMouse(double mouseX, double mouseY) {
        return mouseX > this.guiX + 7 && mouseX < this.guiX + 249 && mouseY > this.guiY + 65 && mouseY < this.guiY + 196;
    }

    @Override
    public void close() {
        super.close();
    }

    private void renderGoalProgress(DrawContext context, int x, int y, int centerX, int centerY, float delta) {
        TrackerScreenUtil.drawScaledText(context, this.textRenderer, lang("goal_progress"), centerX, y + 11, 1, 0xFFFFFF, true);

        int advancementHex = tracker.currentAdvancements() >= tracker.totalAdvancements() ? 0x00FFAA : 0xFFFFFF;
        int rubyHex = tracker.totalRubies() == 0 ? 0x9A9A9A : (tracker.currentRubies() >= tracker.totalRubies() ? 0x00FFAA : 0xFFFFFF);

        drawGoalCheck(context, TROPHY_ICON_TEXTURE, lang("advancements_needed"), tracker.advancements(), x, y, 19, advancementHex);
        drawGoalCheck(context, RUBY_ICON_TEXTURE, lang("rubies_needed"), tracker.rubies(), x, y, 37, rubyHex);
    }

    private void renderChecks(DrawContext context, int x, int y) {
        // Scrollbar
        drawScrollBar(context,  x, y);

        // Tracker Entries
        context.enableScissor(x + 7, y + 65, x + 244, y + 196);
        for (int i = 0; i < tracker.entries().size(); i++) {
            ItemTrackerDataHolder.TrackerEntry entry = tracker.entries().get(i);

            String amount = entry.isProgressive() && entry.count() > 0 ? " (x" + entry.count() + ")" : "";

            drawCheck(context,
                    APMod.id("textures/gui/tracker_icon/" + entry.id() + ".png"),
                    Text.literal(entry.name()).append(amount),
                    x, y, 65 + (i * ENTRY_HEIGHT) - this.scrollOffset, entry.count() > 0
            );
        }
        context.disableScissor();
    }

    private void renderGoalConditionText(DrawContext context, int x, int y) {
        context.drawCenteredTextWithShadow(this.textRenderer, lang("goal"), x, y + 200, 0xFFFFFF);

        MutableText text = Text.empty();
        int goalId = tracker.goal();

        if (goalId > -1) {
            text.append((switch (goalId) {
                case 0 -> lang("goal.dragon");
                case 1 -> lang("goal.wither");
                case 2 -> lang("goal.both");
                case 4 -> lang("goal.ruby_hunt", tracker.totalRubies());
                default -> Text.literal("");
            }).setStyle(Style.EMPTY.withColor(Formatting.AQUA)));

            if (tracker.totalAdvancements() > 0) {
                if (goalId != 3) {
                    text.append(lang("goal.and"));
                }
                text.append(lang("goal.advancements", tracker.totalAdvancements()).setStyle(Style.EMPTY.withColor(Formatting.GREEN)));
            }

            TrackerScreenUtil.drawScaledText(context, this.textRenderer, text, x, y + 215, 0.75f, 0xFFFFFF, true);
        } else {
            text.append(lang("goal.invalid").setStyle(Style.EMPTY.withColor(Formatting.RED)));
            TrackerScreenUtil.drawScaledText(context, this.textRenderer, text, x, y + 215, 0.75f, 0xFFFFFF, true);
        }
    }

    private void drawScrollBar(DrawContext context, int x, int y) {
        if (this.maxScroll > 0) {
            int maxHeight = 131;
            int scrollBarHeight = Math.max(20, (maxHeight * maxHeight) / (maxHeight + this.maxScroll));
            int scrollBarX = x + 245;
            int scrollBarY = (y + 65) + (this.scrollOffset * (maxHeight - scrollBarHeight) / this.maxScroll);

            context.fill(scrollBarX, scrollBarY, scrollBarX + 4, scrollBarY + scrollBarHeight, 0xFFFFFFFF);
        }
    }

    private void drawGoalCheck(DrawContext context, Identifier texture, Text type, Text counter, int x, int y, int yOffset, int hex) {
        context.drawTexture(texture, x + 10, y + yOffset, 0, 0, 16, 16, 16, 16);
        TrackerScreenUtil.drawScaledText(context, this.textRenderer, type, x + 28, y + yOffset + 6, 0.75f, 0xFFFFFF, false);
        TrackerScreenUtil.drawTextWithRightShift(context, this.textRenderer, counter, x + 245, y + yOffset + 6, 0.75f, hex);
    }

    private void drawCheck(DrawContext context, Identifier texture, Text type, int x, int y, int yOffset, boolean unlocked) {
        TrackerScreenUtil.drawGreyscaleTexture(context, texture, x + 10, y + yOffset, !unlocked);
        TrackerScreenUtil.drawScaledText(context, this.textRenderer, type, x + 30, y + yOffset + 6, 0.75f, unlocked ? 0xFFFFFF : 0x820000, false);
    }

    private MutableText lang(String id) {
        return Text.translatable("gui.archipelago." + id);
    }

    private MutableText lang(String id, Object param) {
        return Text.translatable("gui.archipelago." + id, param);
    }

}
