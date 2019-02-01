package com.evacipated.cardcrawl.mod.bard.melodies;

import com.evacipated.cardcrawl.mod.bard.BardMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class WeakenSmallMelody extends AbstractMelody
{
    public static final String ID = BardMod.makeID("WeakenSmall");

    public WeakenSmallMelody()
    {
        super(ID, AbstractCard.CardTarget.ALL_ENEMY);
    }

    @Override
    public void play()
    {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, 1, false), 1, true));
        }
    }

    @Override
    public AbstractCard makeChoiceCard()
    {
        AbstractCard ret = super.makeChoiceCard();
        ret.type = AbstractCard.CardType.SKILL;
        return ret;
    }

    @Override
    public AbstractMelody makeCopy()
    {
        return new WeakenSmallMelody();
    }
}
