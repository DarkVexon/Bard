package com.evacipated.cardcrawl.mod.bard.cards;

import com.evacipated.cardcrawl.mod.bard.BardMod;
import com.evacipated.cardcrawl.mod.bard.actions.unique.QuickSongAction;
import com.evacipated.cardcrawl.mod.bard.characters.Bard;
import com.evacipated.cardcrawl.mod.bard.notes.AbstractNote;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Collections;
import java.util.List;

public class QuickSong extends AbstractBardCard
{
    public static final String ID = BardMod.makeID("QuickSong");
    private static final int COST = 0;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BLOCK = 3;

    public QuickSong()
    {
        super(ID, COST, CardType.SKILL, Bard.Enums.COLOR, CardRarity.COMMON, CardTarget.SELF);

        baseBlock = BLOCK;
    }

    @Override
    public List<AbstractNote> getNotes()
    {
        return Collections.emptyList();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBottom(new GainBlockAction(p, p, block));
        addToBottom(new QuickSongAction(1));
    }

    @Override
    public void upgrade()
    {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BLOCK);
        }
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new QuickSong();
    }
}
