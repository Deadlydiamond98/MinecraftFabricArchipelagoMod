package net.deadlydiamond98.archipelago.archipelago.locations;

import net.deadlydiamond98.archipelago.util.tracker.IAbilityCheck;

import java.util.function.Predicate;

public class APLocationLogic {
    private final Predicate<IAbilityCheck> inLogic;
    private final Predicate<IAbilityCheck> isPossible;

    public APLocationLogic(Predicate<IAbilityCheck> inLogic) {
        this(inLogic, inLogic);
    }

    public APLocationLogic(Predicate<IAbilityCheck> inLogic, Predicate<IAbilityCheck> sequenceBreak) {
        this.inLogic = inLogic;
        this.isPossible = sequenceBreak;
    }

    public boolean isInLogic(IAbilityCheck abilityCheck) {
        return this.inLogic.test(abilityCheck);
    }

    public boolean isPossible(IAbilityCheck abilityCheck) {
        return this.isPossible.test(abilityCheck);
    }
}
