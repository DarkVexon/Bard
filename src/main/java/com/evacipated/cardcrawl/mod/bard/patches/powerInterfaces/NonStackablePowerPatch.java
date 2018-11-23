package com.evacipated.cardcrawl.mod.bard.patches.powerInterfaces;

import com.evacipated.cardcrawl.mod.bard.powers.interfaces.NonStackablePower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NightmarePower;
import javassist.CtBehavior;

import java.util.Iterator;

@SpirePatch(
        clz=ApplyPowerAction.class,
        method="update"
)
public class NonStackablePowerPatch
{
    private static String savedID = null;

    @SpireInsertPatch(
            locator=Locator1.class,
            localvars={"powerToApply", "p"}
    )
    public static void AvoidStacking(ApplyPowerAction __instance, AbstractPower powerToApply, AbstractPower p)
    {
        if (savedID != null) {
            powerToApply.ID = savedID;
            savedID = null;
        }

        if (p.ID.equals(powerToApply.ID)) {
            if (p instanceof NonStackablePower) {
                if (!((NonStackablePower) p).isStackable(powerToApply)) {
                    savedID = powerToApply.ID;
                    powerToApply.ID = NightmarePower.POWER_ID;
                }
            }
        }
    }

    @SpireInsertPatch(
            locator=Locator2.class,
            localvars={"powerToApply"}
    )
    public static void RestorePowerID(ApplyPowerAction __instance, AbstractPower powerToApply)
    {
        if (savedID != null) {
            powerToApply.ID = savedID;
            savedID = null;
        }
    }

    private static class Locator1 extends SpireInsertLocator
    {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
        {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(Iterator.class, "next");
            int[] found = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{found[1]+1};
        }
    }

    private static class Locator2 extends SpireInsertLocator
    {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
        {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "useFastShakeAnimation");
            int[] found = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            --found[0];
            return found;
        }
    }
}
