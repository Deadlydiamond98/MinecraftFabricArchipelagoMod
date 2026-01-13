package net.deadlydiamond98.archipelago.archipelago.recipe;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.deadlydiamond98.archipelago.APMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static net.deadlydiamond98.archipelago.archipelago.recipe.APRecipeChecker.*;

public class APRecipeInfoLoader {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void load(ResourceManager manager) {
        manager.getAllNamespaces().forEach(mod -> {
            Identifier path = new Identifier(mod, "recipelock/progressive_");
            PROGRESSIVE_TOOLS.putAll(load(manager, path, "tools"));
            PROGRESSIVE_WEAPONS.putAll(load(manager, path, "weapons"));
        });
    }

    private static Map<Item, Integer> load(ResourceManager manager, Identifier path, String str) {
        Map<Item, Integer> map = new HashMap<>();
        path = path.withSuffixedPath(str + ".json");
        APMod.LOGGER.info(path.toString());
        try {
            Resource resource = manager.getResource(path).orElseThrow();
            try (InputStream input = resource.getInputStream(); InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                if (APMod.isModLoaded(json.get("mod").getAsString())) {
                    JsonObject entries = json.get("entries").getAsJsonObject();

                    Type type = (new TypeToken<HashMap<String, Integer>>() {}).getType();
                    HashMap<String, Integer> unprocessedEntries = GSON.fromJson(entries, type);

                    unprocessedEntries.forEach((s, integer) -> {
                        map.put(Registries.ITEM.get(new Identifier(s)), integer);
                    });
                }
            } catch (Exception ignored) {
                APMod.LOGGER.info("Failed A {}", ignored);
            }
        } catch (Exception ignored) {}
        return map;
    }
}
