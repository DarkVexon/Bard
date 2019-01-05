package com.evacipated.cardcrawl.mod.bard.cards;

import com.evacipated.cardcrawl.mod.bard.BardMod;
import com.evacipated.cardcrawl.mod.bard.characters.Bard;
import com.evacipated.cardcrawl.mod.bard.notes.AbstractNote;
import com.evacipated.cardcrawl.mod.bard.notes.AttackNote;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Collections;
import java.util.List;

public class StudiedStrike extends AbstractBardCard
{
    public static final String ID = BardMod.makeID("StudiedStrike");
    public static final String IMG = null;
    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_DAMAGE = 4;

    public StudiedStrike()
    {
        super(ID, IMG, COST, CardType.ATTACK, Bard.Enums.COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);

        tags.add(CardTags.STRIKE);
        baseDamage = DAMAGE;
    }

    @Override
    public List<AbstractNote> getNotes()
    {
        return Collections.singletonList(new AttackNote());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        int saveBaseDamage = baseDamage;

        AbstractPower weak = mo.getPower(WeakPower.POWER_ID);
        if (weak != null) {
            baseDamage += weak.amount;
        }

        AbstractPower vuln = mo.getPower(VulnerablePower.POWER_ID);
        if (vuln != null) {
            baseDamage += vuln.amount;
        }

        super.calculateCardDamage(mo);

        baseDamage = saveBaseDamage;
        if (baseDamage != damage) {
            isDamageModified = true;
        }
    }

    @Override
    public void upgrade()
    {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);
        }
    }

    @Override
    public AbstractCard makeCopy()
    {
        return new StudiedStrike();
    }
}