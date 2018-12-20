package com.evacipated.cardcrawl.mod.bard.cards;

import com.evacipated.cardcrawl.mod.bard.BardMod;
import com.evacipated.cardcrawl.mod.bard.actions.common.SelectNoteAction;
import com.evacipated.cardcrawl.mod.bard.characters.Bard;
import com.evacipated.cardcrawl.mod.bard.notes.AbstractNote;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Collections;
import java.util.List;

public class Pluck extends AbstractBardCard
{
    public static final String ID = BardMod.makeID("Pluck");
    public static final String IMG = null;
    private static final int COST = 1;
    private static final int NOTES = 1;
    private static final int UPGRADE_NOTES = 1;
    private static final int DRAW = 1;

    public Pluck()
    {
        super(ID, IMG, COST, CardType.SKILL, Bard.Enums.COLOR, CardRarity.COMMON, CardTarget.NONE);

        magicNumber = baseMagicNumber = NOTES;
    }

    @Override
    public List<AbstractNote> getNotes()
    {
        return Collections.emptyList();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (upgraded) {
            for (int i=0; i<magicNumber; ++i) {
                addToBottom(new SelectNoteAction());
            }
        } else {
            addToBottom(new SelectNoteAction());
        }
        addToBottom(new DrawCardAction(p, DRAW));
    }

    @Override
    public void upgrade()
    {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_NOTES);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new Pluck();
    }
}
