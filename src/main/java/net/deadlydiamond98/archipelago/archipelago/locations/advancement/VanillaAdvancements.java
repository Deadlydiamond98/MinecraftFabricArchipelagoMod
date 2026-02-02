package net.deadlydiamond98.archipelago.archipelago.locations.advancement;

import net.deadlydiamond98.archipelago.APMod;
import net.minecraft.util.Identifier;

import static net.deadlydiamond98.archipelago.archipelago.locations.APLocations.*;

public class VanillaAdvancements {
    public static void addVanillaAdvancements() {
        // Story Advancements
        advancement(new Identifier("story/obtain_armor"));
        advancement(new Identifier("story/lava_bucket"));
        advancement(new Identifier("story/deflect_arrow"));
        advancement(new Identifier("story/iron_tools"));
        advancement(new Identifier("story/mine_stone"));
        advancement(new Identifier("story/enter_the_nether"));
        advancement(new Identifier("story/upgrade_tools"));
        advancement(new Identifier("story/cure_zombie_villager"));
        advancement(new Identifier("story/form_obsidian"));
        advancement(new Identifier("story/smelt_iron"));
        advancement(new Identifier("story/shiny_gear"));
        advancement(new Identifier("story/enchant_item"));
        advancement(new Identifier("story/follow_ender_eye"));
        advancement(new Identifier("story/mine_diamond"));
        advancement(new Identifier("story/enter_the_end"));

        // Adventure Advancements
        advancement(new Identifier("adventure/trim_with_all_exclusive_armor_patterns"));
        advancement(new Identifier("adventure/very_very_frightening"));
        advancement(new Identifier("adventure/lightning_rod_with_villager_no_fire"));
        advancement(new Identifier("adventure/craft_decorated_pot_using_only_sherds"));
        advancement(new Identifier("adventure/kill_mob_near_sculk_catalyst"));
        advancement(new Identifier("adventure/fall_from_world_height"));
        advancement(new Identifier("adventure/sniper_duel"));
        advancement(new Identifier("adventure/bullseye"));
        advancement(new Identifier("adventure/two_birds_one_arrow"));
        advancement(new Identifier("adventure/whos_the_pillager_now"));
        advancement(new Identifier("adventure/walk_on_powder_snow_with_leather_boots"));
        advancement(new Identifier("adventure/salvage_sherd"));
        advancement(new Identifier("adventure/shoot_arrow"));
        advancement(new Identifier("adventure/arbalistic"));
        advancement(new Identifier("adventure/summon_iron_golem"));
        advancement(new Identifier("adventure/avoid_vibration"));
        advancement(new Identifier("adventure/sleep_in_bed"));
        advancement(new Identifier("adventure/kill_all_mobs"));
        advancement(new Identifier("adventure/voluntary_exile"));
        advancement(new Identifier("adventure/spyglass_at_parrot"));
        advancement(new Identifier("adventure/totem_of_undying"));
        advancement(new Identifier("adventure/kill_a_mob"));
        advancement(new Identifier("adventure/adventuring_time"));
        advancement(new Identifier("adventure/spyglass_at_dragon"));
        advancement(new Identifier("adventure/trade_at_world_height"));
        advancement(new Identifier("adventure/play_jukebox_in_meadows"));
        advancement(new Identifier("adventure/hero_of_the_village"));
        advancement(new Identifier("adventure/read_power_of_chiseled_bookshelf"));
        advancement(new Identifier("adventure/trade"));
        advancement(new Identifier("adventure/spyglass_at_ghast"));
        advancement(new Identifier("adventure/trim_with_any_armor_pattern"));
        advancement(new Identifier("adventure/throw_trident"));
        advancement(new Identifier("adventure/honey_block_slide"));
        advancement(new Identifier("adventure/ol_betsy"));

        // Husbandry Advancements
        advancement(new Identifier("husbandry/allay_deliver_item_to_player"));
        advancement(new Identifier("husbandry/froglights"));
        advancement(new Identifier("husbandry/ride_a_boat_with_a_goat"));
        advancement(new Identifier("husbandry/tame_an_animal"));
        advancement(new Identifier("husbandry/make_a_sign_glow"));
        advancement(new Identifier("husbandry/leash_all_frog_variants"));
        advancement(new Identifier("husbandry/fishy_business"));
        advancement(new Identifier("husbandry/bred_all_animals"));
        advancement(new Identifier("husbandry/tactical_fishing"));
        advancement(new Identifier("husbandry/feed_snifflet"));
        advancement(new Identifier("husbandry/silk_touch_nest"));
        advancement(new Identifier("husbandry/tadpole_in_a_bucket"));
        advancement(new Identifier("husbandry/wax_off"));
        advancement(new Identifier("husbandry/obtain_sniffer_egg"));
        advancement(new Identifier("husbandry/obtain_netherite_hoe"));
        advancement(new Identifier("husbandry/plant_any_sniffer_seed"));
        advancement(new Identifier("husbandry/plant_seed"));
        advancement(new Identifier("husbandry/axolotl_in_a_bucket"));
        advancement(new Identifier("husbandry/allay_deliver_cake_to_note_block"));
        advancement(new Identifier("husbandry/wax_on"));
        advancement(new Identifier("husbandry/balanced_diet"));
        advancement(new Identifier("husbandry/safely_harvest_honey"));
        advancement(new Identifier("husbandry/kill_axolotl_target"));
        advancement(new Identifier("husbandry/breed_an_animal"));
        advancement(new Identifier("husbandry/complete_catalogue"));

        // Nether Advancements
        advancement(new Identifier("nether/obtain_crying_obsidian"));
        advancement(new Identifier("nether/distract_piglin"));
        advancement(new Identifier("nether/all_potions"));
        advancement(new Identifier("nether/create_beacon"));
        advancement(new Identifier("nether/brew_potion"));
        advancement(new Identifier("nether/explore_nether"));
        advancement(new Identifier("nether/ride_strider"));
        advancement(new Identifier("nether/all_effects"));
        advancement(new Identifier("nether/get_wither_skull"));
        advancement(new Identifier("nether/obtain_blaze_rod"));
        advancement(new Identifier("nether/loot_bastion"));
        advancement(new Identifier("nether/charge_respawn_anchor"));
        advancement(new Identifier("nether/return_to_sender"));
        advancement(new Identifier("nether/find_bastion"));
        advancement(new Identifier("nether/ride_strider_in_overworld_lava"));
        advancement(new Identifier("nether/obtain_ancient_debris"));
        advancement(new Identifier("nether/create_full_beacon"));
        advancement(new Identifier("nether/summon_wither"));
        advancement(new Identifier("nether/fast_travel"));
        advancement(new Identifier("nether/use_lodestone"));
        advancement(new Identifier("nether/uneasy_alliance"));
        advancement(new Identifier("nether/find_fortress"));
        advancement(new Identifier("nether/netherite_armor"));

        // End Advancements
        advancement(new Identifier("end/kill_dragon"));
        advancement(new Identifier("end/dragon_egg"));
        advancement(new Identifier("end/levitate"));
        advancement(new Identifier("end/find_end_city"));
        advancement(new Identifier("end/enter_end_gateway"));
        advancement(new Identifier("end/respawn_dragon"));
        advancement(new Identifier("end/elytra"));
        advancement(new Identifier("end/dragon_breath"));
    }

    public static void addLegacyAdvancements() {
        // Custom Archipelago Advancements

        // Legacy / 1.12
        advancement(APMod.id("legacy/getting_wood"));
        advancement(APMod.id("legacy/has_crafting_table"));
        advancement(APMod.id("legacy/wood_pickaxe"));
        advancement(APMod.id("legacy/has_furnace"));
        advancement(APMod.id("legacy/wood_hoe"));
        advancement(APMod.id("legacy/get_bread"));
        advancement(APMod.id("legacy/wood_sword"));
        advancement(APMod.id("legacy/the_lie"));
        advancement(APMod.id("legacy/get_fish"));
        advancement(APMod.id("legacy/kill_cow"));
        advancement(APMod.id("legacy/on_a_rail"));
        advancement(APMod.id("legacy/overkill"));
        advancement(APMod.id("legacy/get_bookshelf"));
        advancement(APMod.id("legacy/eat_golden_apple"));
        advancement(APMod.id("legacy/when_pigs_fly"));
    }
}
