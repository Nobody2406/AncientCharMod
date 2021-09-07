package theAncient.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import theAncient.DefaultMod;
import theAncient.characters.TheAncient;
import theAncient.powers.DrawReductionSingleTurnPower;
import theAncient.powers.PrestigePower;

import java.util.Iterator;

import static theAncient.DefaultMod.makeCardPath;

public class ReleasePower extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ReleasePower.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;

    public static final String IMG = makeCardPath("Skill.png");// "public static final String IMG = makeCardPath("AttackTemplate.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.NONE;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;


    // /STAT DECLARATION/


    public ReleasePower() {
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
    this.showEvokeValue = true;
    this.showEvokeOrbCount = 3;
    this.baseMagicNumber = 1;
    this.magicNumber = this.baseMagicNumber;
    this.selfRetain = true;
}


    // Actions the card should do.
    @Override
    public void onRetained() {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new DrawReductionSingleTurnPower(AbstractDungeon.player,AbstractDungeon.player, 1), 1));
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (this.upgraded) {
            int count = -1;
            Iterator var4 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var4.hasNext()) {
                AbstractMonster mon = (AbstractMonster)var4.next();
                if (!mon.isDeadOrEscaped()) {
                    ++count;
                }
            }

            for(int i = 0; i < count * this.magicNumber; ++i) {
                this.addToBot(new AnimateOrbAction(1));
                this.addToBot(new EvokeWithoutRemovingOrbAction(1));
            }

        }
            AbstractDungeon.actionManager.addToBottom(
                    new AnimateOrbAction(1));
            AbstractDungeon.actionManager.addToBottom(
                    new EvokeOrbAction(1));

            AbstractDungeon.actionManager.addToBottom(
                    new DecreaseMaxOrbAction(1));

            this.exhaust = true;
        }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
