package com.evacipated.cardcrawl.mod.bard.actions.unique;

import com.evacipated.cardcrawl.mod.bard.BardMod;
import com.evacipated.cardcrawl.mod.bard.cards.MnemonicVestments;
import com.evacipated.cardcrawl.mod.bard.vfx.cardManip.ShowThisCardAndAddToDrawPileEffect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class MnemonicVestmentsAction extends AbstractGameAction
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(BardMod.makeID("MnemonicVestmentsAction"));
    public static final String[] TEXT = uiStrings.TEXT;

    public MnemonicVestmentsAction()
    {
        actionType = ActionType.CARD_MANIPULATION;
        duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update()
    {
        if (duration == Settings.ACTION_DUR_MED) {
            AbstractDungeon.gridSelectScreen.open(getCards(), 1, TEXT[0], false);
            tickDuration();
            return;
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractCard copy = makeAlteredCopy(c);
                AbstractCard displayCopy = makeAlteredCopy(c);

                AbstractDungeon.effectList.add(new ShowThisCardAndAddToDrawPileEffect(
                        copy,
                        displayCopy,
                        Settings.WIDTH / 2.0f,
                        Settings.HEIGHT / 2.0f,
                        true,
                        true,
                        false
                ));
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();

            isDone = true;
        }
        tickDuration();
    }

    private CardGroup getCards()
    {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (!card.cardID.equals(MnemonicVestments.ID)) {
                group.addToTop(card);
            }
        }
        return group;
    }

    private AbstractCard makeAlteredCopy(AbstractCard card)
    {
        AbstractCard copy = card.makeSameInstanceOf();

        if (copy.cost > 0) {
            copy.freeToPlayOnce = true;
        }

        return copy;
    }
}
