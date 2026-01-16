package net.deadlydiamond98.archipelago.archipelago.items;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Loads Items from json for determining what items are locked behind what progressive tiers
 */
public class APItemDataLoader implements SimpleSynchronousResourceReloadListener {
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
            APItemAccessUtil.PROGRESSIVE_ITEM_IDS.forEach(id -> {
                Map<Item, Integer> progressiveItems = APItemAccessUtil.PROGRESSIVE_ITEMS.get(id);
                progressiveItems.putAll(loadProgressive(manager, path, id));
            });
            APItemAccessUtil.BOOLEAN_ITEM_IDS.forEach(id -> {
                List<Item> progressiveItems = APItemAccessUtil.BOOLEAN_ITEMS.get(id);
                progressiveItems.addAll(loadSingle(manager, path, id));
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

    private static List<Item> loadSingle(ResourceManager manager, Identifier path, String str) {
        List<Item> items = new ArrayList<>();
        path = path.withSuffixedPath("/single/" + str + ".json");
        try {
            Resource resource = manager.getResource(path).orElseThrow();
            try (InputStream input = resource.getInputStream(); InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                if (APMod.isModLoaded(json.get("mod").getAsString())) {
                    JsonArray entries = json.get("entries").getAsJsonArray();
                    entries.forEach(s -> {
                        Identifier itemID = new Identifier(s.getAsString());
                        if (APMod.isModLoaded(itemID.getNamespace())) {
                            items.add(Registries.ITEM.get(itemID));
                        }
                    });
                }
            } catch (Exception ignored) {}
        } catch (Exception ignored) {}
        return items;
    }

    public static void register() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new APItemDataLoader());
    }
}
