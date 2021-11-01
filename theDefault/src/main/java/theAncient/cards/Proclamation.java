package theAncient.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.GashAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAncient.DefaultMod;
import theAncient.actions.FameAction;
import theAncient.characters.TheAncient;
import theAncient.characters.TheAncient;
import theAncient.powers.CommonPower;
import theAncient.powers.PrestigePower;

import static theAncient.DefaultMod.makeCardPath;

// public class Proclamation extends AbstractDynamicCard

public class Proclamation extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Proclamation.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;

    public static final String IMG = makeCardPath("Skill.png");// "public static final String IMG = makeCardPath("Proclamation.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = TheAncient.Enums.COLOR_ANCIENT_YELLOW;

    private static final int COST = 1;  // COST = 1
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = 1

    private static final int PRESTIGE = 1;    // DAMAGE = 0
    private static final int UPGRADE_PLUS_PRESTIGE = 1;
    private static final int CARDS = 1;    // DAMAGE = 0
    private static final int UPGRADE_PLUS_CARDS = 1;    // DAMAGE = 0
    private static final int FAME = 1;    // DAMAGE = 0

    // /STAT DECLARATION/


    public Proclamation() { // public Proclamation() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = PRESTIGE;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = CARDS;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.defaultSecondMagicNumber));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PrestigePower(p, p, magicNumber), magicNumber));

        this.addToBot(new FameAction(this, this.FAME));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_PRESTIGE);
            this.upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_CARDS);
            initializeDescription();
        }
    }
}

