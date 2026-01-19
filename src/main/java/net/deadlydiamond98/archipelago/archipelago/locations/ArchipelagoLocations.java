package net.deadlydiamond98.archipelago.archipelago.locations;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.deadlydiamond98.archipelago.APMod;
import net.minecraft.util.Identifier;

public class ArchipelagoLocations {

    public static final BiMap<Identifier, Long> LOCATIONS = HashBiMap.create();
    private static long i = 1;

    static {
        addVanillaAdvancements();
        addLegacyAdvancements();
    }

    private static void addVanillaAdvancements() {
        // Story Advancements
        addLocation(new Identifier("story/obtain_armor"));
        addLocation(new Identifier("story/lava_bucket"));
        addLocation(new Identifier("story/deflect_arrow"));
        addLocation(new Identifier("story/iron_tools"));
        addLocation(new Identifier("story/mine_stone"));
        addLocation(new Identifier("story/enter_the_nether"));
        addLocation(new Identifier("story/upgrade_tools"));
        addLocation(new Identifier("story/cure_zombie_villager"));
        addLocation(new Identifier("story/form_obsidian"));
        addLocation(new Identifier("story/smelt_iron"));
        addLocation(new Identifier("story/shiny_gear"));
        addLocation(new Identifier("story/enchant_item"));
        addLocation(new Identifier("story/follow_ender_eye"));
        addLocation(new Identifier("story/mine_diamond"));
        addLocation(new Identifier("story/enter_the_end"));

        // Adventure Advancements
        addLocation(new Identifier("adventure/trim_with_all_exclusive_armor_patterns"));
        addLocation(new Identifier("adventure/very_very_frightening"));
        addLocation(new Identifier("adventure/lightning_rod_with_villager_no_fire"));
        addLocation(new Identifier("adventure/craft_decorated_pot_using_only_sherds"));
        addLocation(new Identifier("adventure/kill_mob_near_sculk_catalyst"));
        addLocation(new Identifier("adventure/fall_from_world_height"));
        addLocation(new Identifier("adventure/sniper_duel"));
        addLocation(new Identifier("adventure/bullseye"));
        addLocation(new Identifier("adventure/two_birds_one_arrow"));
        addLocation(new Identifier("adventure/whos_the_pillager_now"));
        addLocation(new Identifier("adventure/walk_on_powder_snow_with_leather_boots"));
        addLocation(new Identifier("adventure/salvage_sherd"));
        addLocation(new Identifier("adventure/shoot_arrow"));
        addLocation(new Identifier("adventure/arbalistic"));
        addLocation(new Identifier("adventure/summon_iron_golem"));
        addLocation(new Identifier("adventure/avoid_vibration"));
        addLocation(new Identifier("adventure/sleep_in_bed"));
        addLocation(new Identifier("adventure/kill_all_mobs"));
        addLocation(new Identifier("adventure/voluntary_exile"));
        addLocation(new Identifier("adventure/spyglass_at_parrot"));
        addLocation(new Identifier("adventure/totem_of_undying"));
        addLocation(new Identifier("adventure/kill_a_mob"));
        addLocation(new Identifier("adventure/adventuring_time"));
        addLocation(new Identifier("adventure/spyglass_at_dragon"));
        addLocation(new Identifier("adventure/trade_at_world_height"));
        addLocation(new Identifier("adventure/play_jukebox_in_meadows"));
        addLocation(new Identifier("adventure/hero_of_the_village"));
        addLocation(new Identifier("adventure/read_power_of_chiseled_bookshelf"));
        addLocation(new Identifier("adventure/trade"));
        addLocation(new Identifier("adventure/spyglass_at_ghast"));
        addLocation(new Identifier("adventure/trim_with_any_armor_pattern"));
        addLocation(new Identifier("adventure/throw_trident"));
        addLocation(new Identifier("adventure/honey_block_slide"));
        addLocation(new Identifier("adventure/ol_betsy"));

        // Husbandry Advancements
        addLocation(new Identifier("husbandry/allay_deliver_item_to_player"));
        addLocation(new Identifier("husbandry/froglights"));
        addLocation(new Identifier("husbandry/ride_a_boat_with_a_goat"));
        addLocation(new Identifier("husbandry/tame_an_animal"));
        addLocation(new Identifier("husbandry/make_a_sign_glow"));
        addLocation(new Identifier("husbandry/leash_all_frog_variants"));
        addLocation(new Identifier("husbandry/fishy_business"));
        addLocation(new Identifier("husbandry/bred_all_animals"));
        addLocation(new Identifier("husbandry/tactical_fishing"));
        addLocation(new Identifier("husbandry/feed_snifflet"));
        addLocation(new Identifier("husbandry/silk_touch_nest"));
        addLocation(new Identifier("husbandry/tadpole_in_a_bucket"));
        addLocation(new Identifier("husbandry/wax_off"));
        addLocation(new Identifier("husbandry/obtain_sniffer_egg"));
        addLocation(new Identifier("husbandry/obtain_netherite_hoe"));
        addLocation(new Identifier("husbandry/plant_any_sniffer_seed"));
        addLocation(new Identifier("husbandry/plant_seed"));
        addLocation(new Identifier("husbandry/axolotl_in_a_bucket"));
        addLocation(new Identifier("husbandry/allay_deliver_cake_to_note_block"));
        addLocation(new Identifier("husbandry/wax_on"));
        addLocation(new Identifier("husbandry/balanced_diet"));
        addLocation(new Identifier("husbandry/safely_harvest_honey"));
        addLocation(new Identifier("husbandry/kill_axolotl_target"));
        addLocation(new Identifier("husbandry/breed_an_animal"));
        addLocation(new Identifier("husbandry/complete_catalogue"));

        // Nether Advancements
        addLocation(new Identifier("nether/obtain_crying_obsidian"));
        addLocation(new Identifier("nether/distract_piglin"));
        addLocation(new Identifier("nether/all_potions"));
        addLocation(new Identifier("nether/create_beacon"));
        addLocation(new Identifier("nether/brew_potion"));
        addLocation(new Identifier("nether/explore_nether"));
        addLocation(new Identifier("nether/ride_strider"));
        addLocation(new Identifier("nether/all_effects"));
        addLocation(new Identifier("nether/get_wither_skull"));
        addLocation(new Identifier("nether/obtain_blaze_rod"));
        addLocation(new Identifier("nether/loot_bastion"));
        addLocation(new Identifier("nether/charge_respawn_anchor"));
        addLocation(new Identifier("nether/return_to_sender"));
        addLocation(new Identifier("nether/find_bastion"));
        addLocation(new Identifier("nether/ride_strider_in_overworld_lava"));
        addLocation(new Identifier("nether/obtain_ancient_debris"));
        addLocation(new Identifier("nether/create_full_beacon"));
        addLocation(new Identifier("nether/summon_wither"));
        addLocation(new Identifier("nether/fast_travel"));
        addLocation(new Identifier("nether/use_lodestone"));
        addLocation(new Identifier("nether/uneasy_alliance"));
        addLocation(new Identifier("nether/find_fortress"));
        addLocation(new Identifier("nether/netherite_armor"));

        // End Advancements
        addLocation(new Identifier("end/kill_dragon"));
        addLocation(new Identifier("end/dragon_egg"));
        addLocation(new Identifier("end/levitate"));
        addLocation(new Identifier("end/find_end_city"));
        addLocation(new Identifier("end/enter_end_gateway"));
        addLocation(new Identifier("end/respawn_dragon"));
        addLocation(new Identifier("end/elytra"));
        addLocation(new Identifier("end/dragon_breath"));
    }

    private static void addLegacyAdvancements() {
        // Custom Archipelago Advancements
        addLocation(APMod.id("legacy/getting_wood"));
        addLocation(APMod.id("legacy/has_crafting_table"));
        addLocation(APMod.id("legacy/wood_pickaxe"));
        addLocation(APMod.id("legacy/has_furnace"));
        addLocation(APMod.id("legacy/wood_hoe"));
        addLocation(APMod.id("legacy/get_bread"));
        addLocation(APMod.id("legacy/wood_sword"));
        addLocation(APMod.id("legacy/the_lie"));
        addLocation(APMod.id("legacy/get_fish"));
        addLocation(APMod.id("legacy/kill_cow"));
        addLocation(APMod.id("legacy/on_a_rail"));
        addLocation(APMod.id("legacy/overkill"));
        addLocation(APMod.id("legacy/get_bookshelf"));
        addLocation(APMod.id("legacy/eat_golden_apple"));
        addLocation(APMod.id("legacy/when_pigs_fly"));
    }

    private static void addLocation(Identifier id) {
        LOCATIONS.put(id, i++);
    }
}
