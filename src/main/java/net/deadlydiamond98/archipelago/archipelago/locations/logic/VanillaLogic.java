package net.deadlydiamond98.archipelago.archipelago.locations.logic;

import net.deadlydiamond98.archipelago.util.tracker.IAbilityCheck;

import java.util.function.Predicate;

public class VanillaLogic {

    public static Predicate<IAbilityCheck> alwaysAvailable() {
        return abilityCheck -> true;
    }

    // OPTIONAL ABILITY CHECKS /////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canSprint() {
        return abilityCheck -> abilityCheck.has("sprint");
    }

    public static Predicate<IAbilityCheck> canJump() {
        return abilityCheck -> abilityCheck.has("jump");
    }

    public static Predicate<IAbilityCheck> canUseChests() {
        return abilityCheck -> abilityCheck.has("chests");
    }

    public static Predicate<IAbilityCheck> canSwim() {
        return abilityCheck -> abilityCheck.has("swim");
    }

//    public static Predicate<IAbilityCheck> speedrunnerMode() {
//        return canSleep();
//    }

//    public static Predicate<IAbilityCheck> hasOptionalGoalAbilities() {
//        return canJump().and(canSprint());
//    }

    // ABILITY CHECKS //////////////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canTrade() {
        return abilityCheck -> abilityCheck.has("trading");
    }

    public static Predicate<IAbilityCheck> canBarter() {
        return canSmelt().and(canAccessNether().and(abilityCheck -> abilityCheck.has("bartering")));
    }

    public static Predicate<IAbilityCheck> canSleep() {
        return abilityCheck -> abilityCheck.has("spawn_point");
    }

    // CRAFTING STATION CHECKS /////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canSmelt() {
        return canUseStoneTools().and(abilityCheck -> abilityCheck.has("smelting", 1));
    }

    public static Predicate<IAbilityCheck> canSmeltBetter() {
        return canUseStoneTools().and(abilityCheck -> abilityCheck.has("smelting", 2));
    }

    public static Predicate<IAbilityCheck> canSmith() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("smithing"));
    }

    public static Predicate<IAbilityCheck> canBrew() {
        return canAccessNether().and(canUseBottles().and(abilityCheck -> abilityCheck.has("brewing")));
    }

    public static Predicate<IAbilityCheck> canEnchant() {
        return canUseDiamondTools().and(canCompactResources().and(abilityCheck -> abilityCheck.has("enchanting")));
    }

    public static Predicate<IAbilityCheck> canAccessMiscJobSites() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("misc_stations"));
    }

    // MINING TOOL CHECKS //////////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canUseStoneTools() {
        return abilityCheck -> abilityCheck.has("tools", 1);
    }

    public static Predicate<IAbilityCheck> canUseIronTools() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("tools", 2));
    }

    public static Predicate<IAbilityCheck> canUseDiamondTools() {
        return canUseIronTools().and(abilityCheck -> abilityCheck.has("tools", 3));
    }

    public static Predicate<IAbilityCheck> canUseNetheriteTools() {
        return canUseDiamondTools().and(canSmith().and(canGetUpgradeTemplate().and(abilityCheck -> abilityCheck.has("tools", 4))));
    }

    // WEAPON CHECKS ///////////////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canUseStoneWeapons() {
        return abilityCheck -> abilityCheck.has("weapons", 1);
    }

    public static Predicate<IAbilityCheck> canUseIronWeapons() {
        return canUseStoneTools().and(canSmelt().and(abilityCheck -> abilityCheck.has("weapons", 2)));
    }

    public static Predicate<IAbilityCheck> canUseDiamondWeapons() {
        return canUseIronTools().and(abilityCheck -> abilityCheck.has("weapons", 3));
    }

    public static Predicate<IAbilityCheck> canUseNetheriteWeapons() {
        return canUseDiamondTools().and(canSmith().and(canGetUpgradeTemplate().and(abilityCheck -> abilityCheck.has("weapons", 4))));
    }

    // ARMOR CHECKS ////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canWearLeatherArmor() {
        return abilityCheck -> abilityCheck.has("armor", 1);
    }

    public static Predicate<IAbilityCheck> canWearGoldArmor() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("armor", 2));
    }

    public static Predicate<IAbilityCheck> canWearIronArmor() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("armor", 3));
    }

    public static Predicate<IAbilityCheck> canWearDiamondArmor() {
        return canUseIronTools().and(abilityCheck -> abilityCheck.has("armor", 4));
    }

    public static Predicate<IAbilityCheck> canWearNetheriteArmor() {
        return canSmith().and(canUseDiamondTools().and(canGetUpgradeTemplate().and(abilityCheck -> abilityCheck.has("armor", 5))));
    }

    // OTHER TOOL CHECKS ///////////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canUseBucket() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("bucket"));
    }

    public static Predicate<IAbilityCheck> canUseFlintAndSteel() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("igniter"));
    }

    public static Predicate<IAbilityCheck> canUseMinecart() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("minecarts"));
    }

    public static Predicate<IAbilityCheck> canUseBrush() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("brush"));
    }

    public static Predicate<IAbilityCheck> canUseSpyglass() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("spyglass"));
    }

    public static Predicate<IAbilityCheck> canUseShears() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("shears"));
    }

    public static Predicate<IAbilityCheck> canUseFishingRod() {
        return abilityCheck -> abilityCheck.has("fishing");
    }

    public static Predicate<IAbilityCheck> canUseBottles() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("bottles"));
    }

    public static Predicate<IAbilityCheck> canUseBow() {
        return abilityCheck -> abilityCheck.has("archery", 1);
    }

    public static Predicate<IAbilityCheck> canUseCrossBow() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("archery", 2));
    }

    public static Predicate<IAbilityCheck> canUseShield() {
        return canSmelt().and(abilityCheck -> abilityCheck.has("shield"));
    }

    // OTHER RECIPE CHECKS /////////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canCompactResources() {
        return abilityCheck -> abilityCheck.has("compacting");
    }

    public static Predicate<IAbilityCheck> canGetEyesOfEnder() {
        return canAccessNether().and(abilityCheck -> abilityCheck.has("ender_eye"));
    }

    public static Predicate<IAbilityCheck> canGetAndUseArmorTrims() {
        return canSmith().and(canUseChests().and(canWearLeatherArmor()));
    }

    // DIMENSION CHECKS ////////////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canAccessNether() {
        return (canUseDiamondTools().or(canUseBucket())).and(canUseFlintAndSteel());
    }

    public static Predicate<IAbilityCheck> canAccessEnd() {
        return canGetEyesOfEnder();
    }

    // MISC VANILLA ////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canPlaceBeacon() {
        return canGoalWither().and(canSmelt().and(canCompactResources().and(canUseDiamondTools())));
    }

    public static Predicate<IAbilityCheck> canGetCryingObsidian() {
        return canBarter().or(canUseDiamondTools());
    }

    public static Predicate<IAbilityCheck> canAccessVanillaEndGame() {
        return canEnchant().and(canBrew().and(canPlaceBeacon().and(canBeatDragonAndWither().and(canUseDiamondTools().and(canUseChests())))));
    }

    public static Predicate<IAbilityCheck> canDyeBasic() {
        return abilityCheck -> abilityCheck.has("dyes", 1);
    }

    public static Predicate<IAbilityCheck> canDyeFull() {
        return abilityCheck -> abilityCheck.has("dyes", 2);
    }

    public static Predicate<IAbilityCheck> canDyeBlack() {
        return canDyeBasic().and(canSwim());
    }

    public static Predicate<IAbilityCheck> canGetUpgradeTemplate() {
        return canAccessNether().and(canUseChests());
    }

    public static Predicate<IAbilityCheck> canCureZombieVillager() {
        return canBrew().and((canAccessNether().or(canUseIronTools())));
    }

    public static Predicate<IAbilityCheck> canGetSmoothStone() {
        return canSmelt().or(canEnchant());
    }

    // GOAL CHECKS /////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canGoalEnderDragon() {
        return canAccessEnd();
    }

    public static Predicate<IAbilityCheck> canGoalWither() {
        return canAccessNether().and(abilityCheck -> abilityCheck.has("wither_summoning"));
    }

    public static Predicate<IAbilityCheck> canBeatDragonAndWither() {
        return canGoalEnderDragon().and(canGoalWither());
    }

    // MINIMUM NEEDED //////////////////////////////////////////////////////////////////////////////////////////////////

    public static Predicate<IAbilityCheck> canAccessNetherMinimum() {
        return canUseDiamondTools().or(canUseBucket()).or(canUseChests());
    }

    public static Predicate<IAbilityCheck> canGetDebreeMinimum() {
        return canAccessNetherMinimum().and(canUseDiamondTools().or(canUseChests()));
    }
}
