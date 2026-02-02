package net.deadlydiamond98.archipelago.client;

import net.deadlydiamond98.archipelago.APMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class ArchipelagoItemIconRenderer {
    public static final Identifier ARCHIPELAGO_ITEM_TEXTURE = APMod.id("textures/item/icon/unchecked.png");

    public static void renderIconHandledScreen(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int x, int y) {
        matrices.push();
        matrices.translate((float)(x + 8), (float)(y + 8), 150);
        matrices.multiplyPositionMatrix(new Matrix4f().scaling(1.0F, -1.0F, 1.0F));
        matrices.scale(16.0F, 16.0F, 16.0F);
        matrices.push();
        matrices.scale(0.5f, 0.5f, 1);
        matrices.translate(0, getBobbingY(0.25f), 1);
        draw(matrices, vertexConsumers);
        matrices.pop();
        matrices.pop();
    }

    public static void renderIconItemEntity(MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        matrices.push();

        matrices.translate(0, getBobbingY(0.09375f) + 1, 0);
        Camera camera = MinecraftClient.getInstance().getEntityRenderDispatcher().camera;
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(camera.getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));

        float scale = 0.3f;
        matrices.translate(0.5 * scale, 0.5 * scale, 0.5 * scale);
        matrices.scale(scale, scale, scale);
        matrices.translate(-0.5 * scale, -0.5 * scale, -0.5 * scale);
        matrices.translate(0.5 * scale, 0.5 * scale, -1 * scale);

        draw(matrices, vertexConsumers);
        matrices.pop();
    }

    public static void draw(MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f modelMatrix = entry.getPositionMatrix();
        Matrix3f normalMatrix = entry.getNormalMatrix();

        VertexConsumer vertexConsumer;
        vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(ARCHIPELAGO_ITEM_TEXTURE));

        float minV = 0;
        float maxV = 1;

        int light = LightmapTextureManager.MAX_BLOCK_LIGHT_COORDINATE;
        vertexConsumer.vertex(modelMatrix, 1,  1, 0).color(255, 255, 255, 255).texture(1, minV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0, 1, 0).next();
        vertexConsumer.vertex(modelMatrix,  -1,  1, 0).color(255, 255, 255, 255).texture(0, minV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0, 1, 0).next();
        vertexConsumer.vertex(modelMatrix,  -1, -1, 0).color(255, 255, 255, 255).texture(0, maxV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0, 1, 0).next();
        vertexConsumer.vertex(modelMatrix, 1, -1, 0).color(255, 255, 255, 255).texture(1, maxV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0, 1, 0).next();
    }

    public static float getBobbingY(float intensity) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world != null) {
            return (float) Math.sin((client.getTickDelta() + client.world.getTime()) * intensity) * 0.125f;
        }
        return 0;
    }
}
