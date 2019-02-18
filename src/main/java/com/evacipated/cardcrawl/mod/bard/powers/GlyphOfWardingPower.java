package com.evacipated.cardcrawl.mod.bard.powers;

import com.evacipated.cardcrawl.mod.bard.BardMod;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseTempHpPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GlyphOfWardingPower extends AbstractBardPower implements OnLoseBlockPower, OnLoseTempHpPower
{
    public static final String POWER_ID = BardMod.makeID("GlyphOfWarding");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GlyphOfWardingPower(AbstractCreature owner, int damage)
    {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        type = PowerType.BUFF;
        this.amount = damage;
        updateDescription();
        loadRegion("glyphOfWarding");
    }

    @Override
    public void updateDescription()
    {
        if (owner == null || owner.isPlayer) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
        }
    }

    @Override
    public int onLoseBlock(DamageInfo info, int damageAmount)
    {
        return onLoseTempHp(info, damageAmount);
    }

    @Override
    public int onLoseTempHp(DamageInfo info, int damageAmount)
    {
        flash();
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        if (owner == null || owner.isPlayer) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAllEnemiesAction(
                            owner,
                            DamageInfo.createDamageMatrix(amount, true),
                            DamageInfo.DamageType.THORNS,
                            AbstractGameAction.AttackEffect.FIRE
                    )
            );
        } else {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(
                            AbstractDungeon.player,
                            new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS),
                            AbstractGameAction.AttackEffect.FIRE
                    )
            );
        }

        return damageAmount;
    }
}
