package net.deadlydiamond98.archipelago.archipelago.locations;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.util.Identifier;

public class ArchipelagoLocations {
    public static final BiMap<Identifier, Long> LOCATIONS = HashBiMap.create();
    private static long i = 1;

    static {
        addVanillaAdvancements();
    }

    private static void addVanillaAdvancements() {
        // Story Advancements
        addLocation(new Identifier("minecraft:story/obtain_armor"));
        addLocation(new Identifier("minecraft:story/lava_bucket"));
        addLocation(new Identifier("minecraft:story/deflect_arrow"));
        addLocation(new Identifier("minecraft:story/iron_tools"));
        addLocation(new Identifier("minecraft:story/mine_stone"));
        addLocation(new Identifier("minecraft:story/enter_the_nether"));
        addLocation(new Identifier("minecraft:story/upgrade_tools"));
        addLocation(new Identifier("minecraft:story/cure_zombie_villager"));
        addLocation(new Identifier("minecraft:story/form_obsidian"));
        addLocation(new Identifier("minecraft:story/smelt_iron"));
        addLocation(new Identifier("minecraft:story/shiny_gear"));
        addLocation(new Identifier("minecraft:story/enchant_item"));
        addLocation(new Identifier("minecraft:story/follow_ender_eye"));
        addLocation(new Identifier("minecraft:story/mine_diamond"));
        addLocation(new Identifier("minecraft:story/enter_the_end"));

        // Adventure Advancements
        addLocation(new Identifier("minecraft:adventure/trim_with_all_exclusive_armor_patterns"));
        addLocation(new Identifier("minecraft:adventure/very_very_frightening"));
        addLocation(new Identifier("minecraft:adventure/lightning_rod_with_villager_no_fire"));
        addLocation(new Identifier("minecraft:adventure/craft_decorated_pot_using_only_sherds"));
        addLocation(new Identifier("minecraft:adventure/kill_mob_near_sculk_catalyst"));
        addLocation(new Identifier("minecraft:adventure/fall_from_world_height"));
        addLocation(new Identifier("minecraft:adventure/sniper_duel"));
        addLocation(new Identifier("minecraft:adventure/bullseye"));
        addLocation(new Identifier("minecraft:adventure/two_birds_one_arrow"));
        addLocation(new Identifier("minecraft:adventure/whos_the_pillager_now"));
        addLocation(new Identifier("minecraft:adventure/walk_on_powder_snow_with_leather_boots"));
        addLocation(new Identifier("minecraft:adventure/salvage_sherd"));
        addLocation(new Identifier("minecraft:adventure/shoot_arrow"));
        addLocation(new Identifier("minecraft:adventure/arbalistic"));
        addLocation(new Identifier("minecraft:adventure/summon_iron_golem"));
        addLocation(new Identifier("minecraft:adventure/avoid_vibration"));
        addLocation(new Identifier("minecraft:adventure/sleep_in_bed"));
        addLocation(new Identifier("minecraft:adventure/kill_all_mobs"));
        addLocation(new Identifier("minecraft:adventure/voluntary_exile"));
        addLocation(new Identifier("minecraft:adventure/spyglass_at_parrot"));
        addLocation(new Identifier("minecraft:adventure/totem_of_undying"));
        addLocation(new Identifier("minecraft:adventure/kill_a_mob"));
        addLocation(new Identifier("minecraft:adventure/adventuring_time"));
        addLocation(new Identifier("minecraft:adventure/spyglass_at_dragon"));
        addLocation(new Identifier("minecraft:adventure/trade_at_world_height"));
        addLocation(new Identifier("minecraft:adventure/play_jukebox_in_meadows"));
        addLocation(new Identifier("minecraft:adventure/hero_of_the_village"));
        addLocation(new Identifier("minecraft:adventure/read_power_of_chiseled_bookshelf"));
        addLocation(new Identifier("minecraft:adventure/trade"));
        addLocation(new Identifier("minecraft:adventure/spyglass_at_ghast"));
        addLocation(new Identifier("minecraft:adventure/trim_with_any_armor_pattern"));
        addLocation(new Identifier("minecraft:adventure/throw_trident"));
        addLocation(new Identifier("minecraft:adventure/honey_block_slide"));
        addLocation(new Identifier("minecraft:adventure/ol_betsy"));

        // Husbandry Advancements
        addLocation(new Identifier("minecraft:husbandry/allay_deliver_item_to_player"));
        addLocation(new Identifier("minecraft:husbandry/froglights"));
        addLocation(new Identifier("minecraft:husbandry/ride_a_boat_with_a_goat"));
        addLocation(new Identifier("minecraft:husbandry/tame_an_animal"));
        addLocation(new Identifier("minecraft:husbandry/make_a_sign_glow"));
        addLocation(new Identifier("minecraft:husbandry/leash_all_frog_variants"));
        addLocation(new Identifier("minecraft:husbandry/fishy_business"));
        addLocation(new Identifier("minecraft:husbandry/bred_all_animals"));
        addLocation(new Identifier("minecraft:husbandry/tactical_fishing"));
        addLocation(new Identifier("minecraft:husbandry/feed_snifflet"));
        addLocation(new Identifier("minecraft:husbandry/silk_touch_nest"));
        addLocation(new Identifier("minecraft:husbandry/tadpole_in_a_bucket"));
        addLocation(new Identifier("minecraft:husbandry/wax_off"));
        addLocation(new Identifier("minecraft:husbandry/obtain_sniffer_egg"));
        addLocation(new Identifier("minecraft:husbandry/obtain_netherite_hoe"));
        addLocation(new Identifier("minecraft:husbandry/plant_any_sniffer_seed"));
        addLocation(new Identifier("minecraft:husbandry/plant_seed"));
        addLocation(new Identifier("minecraft:husbandry/axolotl_in_a_bucket"));
        addLocation(new Identifier("minecraft:husbandry/allay_deliver_cake_to_note_block"));
        addLocation(new Identifier("minecraft:husbandry/wax_on"));
        addLocation(new Identifier("minecraft:husbandry/balanced_diet"));
        addLocation(new Identifier("minecraft:husbandry/safely_harvest_honey"));
        addLocation(new Identifier("minecraft:husbandry/kill_axolotl_target"));
        addLocation(new Identifier("minecraft:husbandry/breed_an_animal"));
        addLocation(new Identifier("minecraft:husbandry/complete_catalogue"));

        // Nether Advancements
        addLocation(new Identifier("minecraft:nether/obtain_crying_obsidian"));
        addLocation(new Identifier("minecraft:nether/distract_piglin"));
        addLocation(new Identifier("minecraft:nether/all_potions"));
        addLocation(new Identifier("minecraft:nether/create_beacon"));
        addLocation(new Identifier("minecraft:nether/brew_potion"));
        addLocation(new Identifier("minecraft:nether/explore_nether"));
        addLocation(new Identifier("minecraft:nether/ride_strider"));
        addLocation(new Identifier("minecraft:nether/all_effects"));
        addLocation(new Identifier("minecraft:nether/get_wither_skull"));
        addLocation(new Identifier("minecraft:nether/obtain_blaze_rod"));
        addLocation(new Identifier("minecraft:nether/loot_bastion"));
        addLocation(new Identifier("minecraft:nether/charge_respawn_anchor"));
        addLocation(new Identifier("minecraft:nether/return_to_sender"));
        addLocation(new Identifier("minecraft:nether/find_bastion"));
        addLocation(new Identifier("minecraft:nether/ride_strider_in_overworld_lava"));
        addLocation(new Identifier("minecraft:nether/obtain_ancient_debris"));
        addLocation(new Identifier("minecraft:nether/create_full_beacon"));
        addLocation(new Identifier("minecraft:nether/summon_wither"));
        addLocation(new Identifier("minecraft:nether/fast_travel"));
        addLocation(new Identifier("minecraft:nether/use_lodestone"));
        addLocation(new Identifier("minecraft:nether/uneasy_alliance"));
        addLocation(new Identifier("minecraft:nether/find_fortress"));
        addLocation(new Identifier("minecraft:nether/netherite_armor"));

        // End Advancements
        addLocation(new Identifier("minecraft:end/kill_dragon"));
        addLocation(new Identifier("minecraft:end/dragon_egg"));
        addLocation(new Identifier("minecraft:end/levitate"));
        addLocation(new Identifier("minecraft:end/find_end_city"));
        addLocation(new Identifier("minecraft:end/enter_end_gateway"));
        addLocation(new Identifier("minecraft:end/respawn_dragon"));
        addLocation(new Identifier("minecraft:end/elytra"));
        addLocation(new Identifier("minecraft:end/dragon_breath"));
    }

    private static void addLocation(Identifier id) {
        LOCATIONS.put(id, i++);
    }
}
