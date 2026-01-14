package net.deadlydiamond98.archipelago.common.archipelago.items;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.util.APItemUtil;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static net.deadlydiamond98.archipelago.util.APItemUtil.*;

/**
 * Loads Items from json for determining what items are locked behind what progressive tiers
 */
public class APRecipeInfoLoader implements SimpleSynchronousResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Identifier getFabricId() {
        return APMod.id("ap_items");
    }

    @Override
    public void reload(ResourceManager manager) {
        manager.getAllNamespaces().forEach(mod -> {
            Identifier path = new Identifier(mod, "ap_items");
            // Gets all Progressive Items from json that need to be locked
            APItemUtil.PROGRESSIVE_ITEM_IDS.forEach(id -> {
                Map<Item, Integer> progressiveItems = PROGRESSIVE_ITEMS.get(id);
                progressiveItems.putAll(loadProgressive(manager, path, id));
            });
        });
    }

    private static Map<Item, Integer> loadProgressive(ResourceManager manager, Identifier path, String str) {
        Map<Item, Integer> map = new HashMap<>();
        path = path.withSuffixedPath("/progressive/" + str + ".json");
        try {
            Resource resource = manager.getResource(path).orElseThrow();
            try (InputStream input = resource.getInputStream(); InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                if (APMod.isModLoaded(json.get("mod").getAsString())) {
                    JsonObject entries = json.get("entries").getAsJsonObject();

                    Type type = (new TypeToken<HashMap<String, Integer>>() {}).getType();
                    HashMap<String, Integer> unprocessedEntries = GSON.fromJson(entries, type);

                    unprocessedEntries.forEach((s, integer) -> {
                        Identifier itemID = new Identifier(s);
                        if (APMod.isModLoaded(itemID.getNamespace())) {
                            map.put(Registries.ITEM.get(itemID), integer);
                        }
                    });
                }
            } catch (Exception ignored) {}
        } catch (Exception ignored) {}
        return map;
    }

    public static void register() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new APRecipeInfoLoader());
    }
}
