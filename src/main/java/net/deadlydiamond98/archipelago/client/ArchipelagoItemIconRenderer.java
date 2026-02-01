package net.deadlydiamond98.archipelago.client;

import net.deadlydiamond98.archipelago.APMod;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class ArchipelagoItemIconRenderer {
    public static final Identifier ARCHIPELAGO_ITEM_TEXTURE = APMod.id("textures/item/icon/unchecked.png");
    public static final List<Item> UNCHECKED_ITEMS = new ArrayList<>();

    public static void renderIcon(MatrixStack matrices, VertexConsumerProvider vertexConsumers, PlayerEntity player, ItemStack stack) {
        matrices.scale(0.5f, 0.5f, 1);
        matrices.translate(0, 0, 1);

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
}
