package net.deadlydiamond98.archipelago.archipelago.items.dataloader;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import net.deadlydiamond98.archipelago.APMod;
import net.deadlydiamond98.archipelago.util.APItemAccessUtil;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * Loads Items from json for determining what items are locked behind what progressive tiers
 */
public class APItemDataLoader implements SimpleSynchronousResourceReloadListener {
    public static final Map<String, Map<String, Integer>> UNPROCESSED_PROGRESSIVE_ITEMS = new HashMap<>();
    public static final Map<String, Set<String>> UNPROCESSED_BOOLEAN_ITEMS = new HashMap<>();
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
                Set<Item> progressiveItems = APItemAccessUtil.BOOLEAN_ITEMS.get(id);
                progressiveItems.addAll(loadSingle(manager, path, id));
            });
        });
    }

    /**
     * WHY IS THIS SEPERATE FROM THE REGULAR DATA LOADING????
     * BECAUSE MINECRAFT DOESN'T LOAD THE TAGS AT THAT POINT!
     * MOJANG??? MORE LIKE MOJANK!
     */
    public static void processItemTags() {
        UNPROCESSED_PROGRESSIVE_ITEMS.forEach((id, map) -> {
            Map<Item, Integer> progressiveItems = APItemAccessUtil.PROGRESSIVE_ITEMS.get(id);
            map.forEach((s, integer) -> getItemTag(s).forEach(item -> {
                progressiveItems.put(item, integer);
            }));
        });
        UNPROCESSED_BOOLEAN_ITEMS.forEach((id, strings) -> {
            Set<Item> progressiveItems = APItemAccessUtil.BOOLEAN_ITEMS.get(id);
            strings.forEach(s -> {
                progressiveItems.addAll(getItemTag(s));
            });
        });
        UNPROCESSED_PROGRESSIVE_ITEMS.clear();
        UNPROCESSED_BOOLEAN_ITEMS.clear();
    }

    private static List<Item> getItemTag(String str) {
        List<Item> items = new ArrayList<>();
        TagKey<Item> itemTag = TagKey.of(RegistryKeys.ITEM, new Identifier(str.substring(1)));
        Registries.ITEM.getEntryList(itemTag).ifPresent(registryEntries -> {
            registryEntries.forEach(itemRegistryEntry -> {
                items.add(itemRegistryEntry.value());
            });
        });
        return items;
    }

    private static Map<Item, Integer> loadProgressive(ResourceManager manager, Identifier path, String str) {
        return load(manager, path, "/progressive/" + str, new HashMap<>(), (json, map) -> {
            JsonObject entries = json.get("entries").getAsJsonObject();
            Type type = (new TypeToken<HashMap<String, Integer>>() {}).getType();
            HashMap<String, Integer> unprocessedEntries = GSON.fromJson(entries, type);
            unprocessedEntries.forEach((s, integer) -> getItemsFromString(str, s, integer, true).forEach(item -> map.put(item, integer)));
        });
    }

    private static List<Item> loadSingle(ResourceManager manager, Identifier path, String str) {
        return load(manager, path, "/single/" + str, new ArrayList<>(), (json, items) -> {
            JsonArray entries = json.get("entries").getAsJsonArray();
            entries.forEach(s -> items.addAll(getItemsFromString(str, s.getAsString(), 0, false)));
        });
    }

    private static <T> T load(ResourceManager manager, Identifier path, String str, T type, BiConsumer<JsonObject, T> consumer) {
        path = path.withSuffixedPath(str + ".json");
        try {
            Resource resource = manager.getResource(path).orElseThrow();
            try (InputStream input = resource.getInputStream(); InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                if (APMod.isModLoaded(json.get("mod").getAsString())) {
                    consumer.accept(json, type);
                }
            } catch (Exception ignored) {}
        } catch (Exception ignored) {}
        return type;
    }

    private static List<Item> getItemsFromString(String key, String str, int i, boolean isProgressive) {
        List<Item> items = new ArrayList<>();
        APMod.LOGGER.info(str);
        if (str.startsWith("#")) {
            if (isProgressive) {
                Map<String, Integer> unprocessedProgressives = UNPROCESSED_PROGRESSIVE_ITEMS.getOrDefault(key, new HashMap<>());
                unprocessedProgressives.put(str, i);
                UNPROCESSED_PROGRESSIVE_ITEMS.put(key, unprocessedProgressives);
            } else {
                Set<String> unprocessedBools = UNPROCESSED_BOOLEAN_ITEMS.getOrDefault(key, new HashSet<>());
                unprocessedBools.add(str);
                UNPROCESSED_BOOLEAN_ITEMS.put(key, unprocessedBools);
            }
        } else {
            Identifier itemID = new Identifier(str);
            if (APMod.isModLoaded(itemID.getNamespace())) {
                items.add(Registries.ITEM.get(itemID));
            }
        }
        return items;
    }

    public static void register() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new APItemDataLoader());
    }
}
