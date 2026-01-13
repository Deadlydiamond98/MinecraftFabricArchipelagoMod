package net.deadlydiamond98.archipelago;

import net.deadlydiamond98.archipelago.archipelago.recipe.APRecipeInfoLoader;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class APReloadListener implements SimpleSynchronousResourceReloadListener {
    @Override
    public Identifier getFabricId() {
        return new Identifier(APMod.MOD_ID, "recipelock");
    }

    @Override
    public void reload(ResourceManager manager) {
        APRecipeInfoLoader.load(manager);
    }
}
