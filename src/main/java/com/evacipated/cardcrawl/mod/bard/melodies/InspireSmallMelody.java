package com.evacipated.cardcrawl.mod.bard.melodies;

import com.evacipated.cardcrawl.mod.bard.BardMod;
import com.evacipated.cardcrawl.mod.bard.powers.InspirationPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class InspireSmallMelody extends AbstractMelody
{
    public static final String ID = BardMod.makeID("InspireSmall");

    public InspireSmallMelody()
    {
        super(ID, AbstractCard.CardTarget.SELF);
    }

    @Override
    public void play()
    {
        addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new InspirationPower(AbstractDungeon.player, 2, 50), 2));
    }

    @Override
    public AbstractMelody makeCopy()
    {
        return new InspireSmallMelody();
    }
}
