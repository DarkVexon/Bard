package com.evacipated.cardcrawl.mod.bard.relics;

import com.evacipated.cardcrawl.mod.bard.BardMod;
import com.evacipated.cardcrawl.mod.bard.cards.AbstractBardCard;
import com.evacipated.cardcrawl.mod.bard.characters.Bard;
import com.evacipated.cardcrawl.mod.bard.notes.AbstractNote;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.List;

public class Lute extends AbstractBardRelic
{
    public static final String ID = BardMod.makeID("Lute");
    public static String NAME = "Lute";

    public Lute()
    {
        super(ID, "test5.png", RelicTier.STARTER, LandingSound.FLAT, Bard.Enums.COLOR);

        NAME = name;
    }

    @Override
    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        List<AbstractNote> notes = AbstractBardCard.determineNoteTypes(card);
        if (!notes.isEmpty()) {
            for (AbstractNote note : notes) {
                System.out.print(note.ascii() + " ");
                // TODO
                if (AbstractDungeon.player instanceof Bard) {
                    ((Bard) AbstractDungeon.player).queueNote(note);
                }
            }
            System.out.println();
        }
    }

    @Override
    public AbstractRelic makeCopy()
    {
        return new Lute();
    }
}
