package theAncient.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAncient.DefaultMod;
import theAncient.characters.TheAncient;

import static theAncient.DefaultMod.makeCardPath;

public class Whipping extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Whipping.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;

    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("AttackTemplate.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheAncient.Enums.COLOR_ANCIENT_YELLOW;

    private static final int COST = 1;

    private static final int DAMAGE = 3;
    private static final int UPGRADE_PLUS_DMG = 1;

    private static final int SINGLE = 7;
    private static final int UPGRADE_PLUS_SINGLE = 2;



    // /STAT DECLARATION/


    public Whipping() {
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
    baseDamage =DAMAGE;
    magicNumber = baseMagicNumber = 1; // number of random enemies attacked
    defaultSecondMagicNumber = defaultBaseSecondMagicNumber = SINGLE;

}

    //single target damage is stored in second magic number, aoe damage in normal damage variable
    @Override
    public void applyPowers() {
        int aoeBase = this.baseDamage; //to reset after

        this.baseDamage = this.defaultBaseSecondMagicNumber; //Calculate damage for single target
        this.isMultiDamage = false;
        super.applyPowers();
        this.defaultSecondMagicNumber = this.damage; //store result in magic number variable
        this.isDefaultSecondMagicNumberModified = this.isDamageModified;

        this.baseDamage = aoeBase;
        this.isMultiDamage = true;
        super.applyPowers(); //calculate normal damage
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        int aoeBase = this.baseDamage; //to reset after

        this.baseDamage = this.defaultBaseSecondMagicNumber; //Calculate damage for single target
        this.isMultiDamage = false;
        super.calculateCardDamage(m);
        this.defaultSecondMagicNumber = this.damage; //store result in magic number variable
        this.isDefaultSecondMagicNumberModified = this.isDamageModified;

        this.baseDamage = aoeBase;
        this.isMultiDamage = true;
        super.calculateCardDamage(m); //calculate normal damage
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, defaultSecondMagicNumber, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        if (this.upgraded) {
            this.isMultiDamage = true;
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        } else {
            for (int i = 0; i < this.magicNumber; ++i) {
                AbstractDungeon.actionManager.addToBottom(
                        new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }

        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_SINGLE);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
