package net.deadlydiamond98.archipelago.util.tracker;

import com.mojang.blaze3d.systems.RenderSystem;
import net.deadlydiamond98.archipelago.init.client.APShaders;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

import java.util.function.BiConsumer;

public class TrackerScreenUtil {

    // TEXTURE HELPER METHODS //////////////////////////////////////////////////////////////////////////////////////////

    public static void drawGreyscaleTexture(DrawContext context, Identifier texture, int x, int y, boolean doGreyscale) {
        drawGreyscaleTexture(context, texture, x, y, 18, 18, doGreyscale);
    }

    public static void drawGreyscaleTexture(DrawContext context, Identifier texture, int x, int y, int width, int height, boolean doGreyscale) {
        if (doGreyscale) {
            drawGreyscaleTexture(context, texture, x, y, width, height);
        } else {
            drawTexture(context, texture, x, y, width, height);
        }
    }

    public static void drawGreyscaleTexture(DrawContext context, Identifier texture, int x, int y, int width, int height) {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(() -> APShaders.greyscaleShader);

        Matrix4f pose = context.getMatrices().peek().getPositionMatrix();

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);

        int x2 = x + width;
        int y2 = y + height;

        buffer.vertex(pose, x, y, 0).texture(0, 0).next();
        buffer.vertex(pose, x, y2, 0).texture(0, 1).next();
        buffer.vertex(pose, x2, y2, 0).texture(1, 1).next();
        buffer.vertex(pose, x2, y, 0).texture(1, 0).next();

        BufferRenderer.drawWithGlobalProgram(buffer.end());
    }

    /**
     * Using this over context.drawTexture() so that I don't have to tell it the texture width and height!
     */
    public static void drawTexture(DrawContext context, Identifier texture, int x, int y, int width, int height) {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);

        Matrix4f pose = context.getMatrices().peek().getPositionMatrix();

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);

        int x2 = x + width;
        int y2 = y + height;

        buffer.vertex(pose, x, y, 0).texture(0, 0).next();
        buffer.vertex(pose, x, y2, 0).texture(0, 1).next();
        buffer.vertex(pose, x2, y2, 0).texture(1, 1).next();
        buffer.vertex(pose, x2, y, 0).texture(1, 0).next();

        BufferRenderer.drawWithGlobalProgram(buffer.end());
    }

    // TEXT HELPER METHODS /////////////////////////////////////////////////////////////////////////////////////////////

    public static void drawScaledText(DrawContext context, TextRenderer textRenderer, Text text, int x, int y, float scale, int color, boolean isCentered) {
        drawWithScale(context, x, y, scale, (x2, y2) -> {
            if (isCentered) {
                context.drawCenteredTextWithShadow(textRenderer, text, x2, y2, color);
            } else {
                context.drawTextWithShadow(textRenderer, text, x2, y2, color);
            }
        });
    }

    public static void drawTextWithRightShift(DrawContext context, TextRenderer textRenderer, Text text, int x, int y, float scale, int color) {
        drawWithScale(context, x, y, scale, (x2, y2) -> {
            context.drawTextWithShadow(textRenderer, text, x2 - textRenderer.getWidth(text), y2, color);
        });
    }

    public static void drawWithScale(DrawContext context, int x, int y, float scale, BiConsumer<Integer, Integer> consumer) {
        MatrixStack matrices = context.getMatrices();
        matrices.push();
        x = (int) (x / scale);
        y = (int) (y / scale);
        matrices.scale(scale, scale, 1);
        consumer.accept(x, y);
        matrices.pop();
    }
}
