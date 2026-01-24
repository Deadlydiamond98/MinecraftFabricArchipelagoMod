package net.deadlydiamond98.archipelago.archipelago.locations.advancement;

import net.deadlydiamond98.archipelago.APMod;
import net.minecraft.util.Identifier;

import static net.deadlydiamond98.archipelago.archipelago.locations.APLocations.*;

public class VanillaAdvancements {
    public static void addVanillaAdvancements() {
        // Story Advancements
        addAdvancmentLocation(new Identifier("story/obtain_armor"));
        addAdvancmentLocation(new Identifier("story/lava_bucket"));
        addAdvancmentLocation(new Identifier("story/deflect_arrow"));
        addAdvancmentLocation(new Identifier("story/iron_tools"));
        addAdvancmentLocation(new Identifier("story/mine_stone"));
        addAdvancmentLocation(new Identifier("story/enter_the_nether"));
        addAdvancmentLocation(new Identifier("story/upgrade_tools"));
        addAdvancmentLocation(new Identifier("story/cure_zombie_villager"));
        addAdvancmentLocation(new Identifier("story/form_obsidian"));
        addAdvancmentLocation(new Identifier("story/smelt_iron"));
        addAdvancmentLocation(new Identifier("story/shiny_gear"));
        addAdvancmentLocation(new Identifier("story/enchant_item"));
        addAdvancmentLocation(new Identifier("story/follow_ender_eye"));
        addAdvancmentLocation(new Identifier("story/mine_diamond"));
        addAdvancmentLocation(new Identifier("story/enter_the_end"));

        // Adventure Advancements
        addAdvancmentLocation(new Identifier("adventure/trim_with_all_exclusive_armor_patterns"), HARD);
        addAdvancmentLocation(new Identifier("adventure/very_very_frightening"), HARD);
        addAdvancmentLocation(new Identifier("adventure/lightning_rod_with_villager_no_fire"), HARD);
        addAdvancmentLocation(new Identifier("adventure/craft_decorated_pot_using_only_sherds"));
        addAdvancmentLocation(new Identifier("adventure/kill_mob_near_sculk_catalyst"), EXPLORATION);
        addAdvancmentLocation(new Identifier("adventure/fall_from_world_height"));
        addAdvancmentLocation(new Identifier("adventure/sniper_duel"));
        addAdvancmentLocation(new Identifier("adventure/bullseye"));
        addAdvancmentLocation(new Identifier("adventure/two_birds_one_arrow"), EXPLORATION);
        addAdvancmentLocation(new Identifier("adventure/whos_the_pillager_now"));
        addAdvancmentLocation(new Identifier("adventure/walk_on_powder_snow_with_leather_boots"), EXPLORATION);
        addAdvancmentLocation(new Identifier("adventure/salvage_sherd"));
        addAdvancmentLocation(new Identifier("adventure/shoot_arrow"));
        addAdvancmentLocation(new Identifier("adventure/arbalistic"));
        addAdvancmentLocation(new Identifier("adventure/summon_iron_golem"));
        addAdvancmentLocation(new Identifier("adventure/avoid_vibration"), EXPLORATION);
        addAdvancmentLocation(new Identifier("adventure/sleep_in_bed"));
        addAdvancmentLocation(new Identifier("adventure/kill_all_mobs"), HARD);
        addAdvancmentLocation(new Identifier("adventure/voluntary_exile"));
        addAdvancmentLocation(new Identifier("adventure/spyglass_at_parrot"));
        addAdvancmentLocation(new Identifier("adventure/totem_of_undying"), EXPLORATION);
        addAdvancmentLocation(new Identifier("adventure/kill_a_mob"));
        addAdvancmentLocation(new Identifier("adventure/adventuring_time"), UNREASONABLE);
        addAdvancmentLocation(new Identifier("adventure/spyglass_at_dragon"));
        addAdvancmentLocation(new Identifier("adventure/trade_at_world_height"));
        addAdvancmentLocation(new Identifier("adventure/play_jukebox_in_meadows"), EXPLORATION);
        addAdvancmentLocation(new Identifier("adventure/hero_of_the_village"));
        addAdvancmentLocation(new Identifier("adventure/read_power_of_chiseled_bookshelf"));
        addAdvancmentLocation(new Identifier("adventure/trade"));
        addAdvancmentLocation(new Identifier("adventure/spyglass_at_ghast"));
        addAdvancmentLocation(new Identifier("adventure/trim_with_any_armor_pattern"));
        addAdvancmentLocation(new Identifier("adventure/throw_trident"));
        addAdvancmentLocation(new Identifier("adventure/honey_block_slide"));
        addAdvancmentLocation(new Identifier("adventure/ol_betsy"));

        // Husbandry Advancements
        addAdvancmentLocation(new Identifier("husbandry/allay_deliver_item_to_player"));
        addAdvancmentLocation(new Identifier("husbandry/froglights"), HARD);
        addAdvancmentLocation(new Identifier("husbandry/ride_a_boat_with_a_goat"), EXPLORATION);
        addAdvancmentLocation(new Identifier("husbandry/tame_an_animal"));
        addAdvancmentLocation(new Identifier("husbandry/make_a_sign_glow"));
        addAdvancmentLocation(new Identifier("husbandry/leash_all_frog_variants"), HARD);
        addAdvancmentLocation(new Identifier("husbandry/fishy_business"));
        addAdvancmentLocation(new Identifier("husbandry/bred_all_animals"), HARD);
        addAdvancmentLocation(new Identifier("husbandry/tactical_fishing"));
        addAdvancmentLocation(new Identifier("husbandry/feed_snifflet"), HARD);
        addAdvancmentLocation(new Identifier("husbandry/silk_touch_nest"));
        addAdvancmentLocation(new Identifier("husbandry/tadpole_in_a_bucket"), EXPLORATION);
        addAdvancmentLocation(new Identifier("husbandry/wax_off"));
        addAdvancmentLocation(new Identifier("husbandry/obtain_sniffer_egg"));
        addAdvancmentLocation(new Identifier("husbandry/obtain_netherite_hoe"), HARD);
        addAdvancmentLocation(new Identifier("husbandry/plant_any_sniffer_seed"), HARD);
        addAdvancmentLocation(new Identifier("husbandry/plant_seed"));
        addAdvancmentLocation(new Identifier("husbandry/axolotl_in_a_bucket"));
        addAdvancmentLocation(new Identifier("husbandry/allay_deliver_cake_to_note_block"));
        addAdvancmentLocation(new Identifier("husbandry/wax_on"));
        addAdvancmentLocation(new Identifier("husbandry/balanced_diet"), HARD);
        addAdvancmentLocation(new Identifier("husbandry/safely_harvest_honey"));
        addAdvancmentLocation(new Identifier("husbandry/kill_axolotl_target"));
        addAdvancmentLocation(new Identifier("husbandry/breed_an_animal"));
        addAdvancmentLocation(new Identifier("husbandry/complete_catalogue"), HARD);

        // Nether Advancements
        addAdvancmentLocation(new Identifier("nether/obtain_crying_obsidian"));
        addAdvancmentLocation(new Identifier("nether/distract_piglin"));
        addAdvancmentLocation(new Identifier("nether/all_potions"), HARD);
        addAdvancmentLocation(new Identifier("nether/create_beacon"));
        addAdvancmentLocation(new Identifier("nether/brew_potion"));
        addAdvancmentLocation(new Identifier("nether/explore_nether"), EXPLORATION);
        addAdvancmentLocation(new Identifier("nether/ride_strider"));
        addAdvancmentLocation(new Identifier("nether/all_effects"), UNREASONABLE);
        addAdvancmentLocation(new Identifier("nether/get_wither_skull"));
        addAdvancmentLocation(new Identifier("nether/obtain_blaze_rod"));
        addAdvancmentLocation(new Identifier("nether/loot_bastion"));
        addAdvancmentLocation(new Identifier("nether/charge_respawn_anchor"));
        addAdvancmentLocation(new Identifier("nether/return_to_sender"));
        addAdvancmentLocation(new Identifier("nether/find_bastion"));
        addAdvancmentLocation(new Identifier("nether/ride_strider_in_overworld_lava"));
        addAdvancmentLocation(new Identifier("nether/obtain_ancient_debris"));
        addAdvancmentLocation(new Identifier("nether/create_full_beacon"), HARD);
        addAdvancmentLocation(new Identifier("nether/summon_wither"));
        addAdvancmentLocation(new Identifier("nether/fast_travel"));
        addAdvancmentLocation(new Identifier("nether/use_lodestone"));
        addAdvancmentLocation(new Identifier("nether/uneasy_alliance"));
        addAdvancmentLocation(new Identifier("nether/find_fortress"));
        addAdvancmentLocation(new Identifier("nether/netherite_armor"), HARD);

        // End Advancements
        addAdvancmentLocation(new Identifier("end/kill_dragon"));
        addAdvancmentLocation(new Identifier("end/dragon_egg"));
        addAdvancmentLocation(new Identifier("end/levitate"));
        addAdvancmentLocation(new Identifier("end/find_end_city"));
        addAdvancmentLocation(new Identifier("end/enter_end_gateway"));
        addAdvancmentLocation(new Identifier("end/respawn_dragon"));
        addAdvancmentLocation(new Identifier("end/elytra"));
        addAdvancmentLocation(new Identifier("end/dragon_breath"));
    }

    public static void addLegacyAdvancements() {
        // Custom Archipelago Advancements

        // Legacy / 1.12
        addAdvancmentLocation(APMod.id("legacy/getting_wood"));
        addAdvancmentLocation(APMod.id("legacy/has_crafting_table"));
        addAdvancmentLocation(APMod.id("legacy/wood_pickaxe"));
        addAdvancmentLocation(APMod.id("legacy/has_furnace"));
        addAdvancmentLocation(APMod.id("legacy/wood_hoe"));
        addAdvancmentLocation(APMod.id("legacy/get_bread"));
        addAdvancmentLocation(APMod.id("legacy/wood_sword"));
        addAdvancmentLocation(APMod.id("legacy/the_lie"));
        addAdvancmentLocation(APMod.id("legacy/get_fish"));
        addAdvancmentLocation(APMod.id("legacy/kill_cow"));
        addAdvancmentLocation(APMod.id("legacy/on_a_rail"));
        addAdvancmentLocation(APMod.id("legacy/overkill"));
        addAdvancmentLocation(APMod.id("legacy/get_bookshelf"));
        addAdvancmentLocation(APMod.id("legacy/eat_golden_apple"), HARD);
        addAdvancmentLocation(APMod.id("legacy/when_pigs_fly"));
    }
}
