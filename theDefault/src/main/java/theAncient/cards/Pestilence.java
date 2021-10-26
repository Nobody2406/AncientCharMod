package theAncient.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import theAncient.DefaultMod;
import theAncient.characters.TheAncient;
import theAncient.orbs.DefaultOrb;

import static theAncient.DefaultMod.makeCardPath;

public class Pestilence extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Pestilence.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;

    public static final String IMG = makeCardPath("Skill.png");// "public static final String IMG = makeCardPath("AttackTemplate.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = TheAncient.Enums.COLOR_ANCIENT_YELLOW;

    private static final int COST = 1;

    private static final int ORBS = 1;
    private static final int UPGRADE_PLUS_ORBS = 1;
    private static final int SLOTS = 1;
    private static final int UPGRADE_PLUS_SLOTS = 0;

    // /STAT DECLARATION/


    public Pestilence(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
    magicNumber = baseMagicNumber = ORBS;
    defaultSecondMagicNumber = defaultBaseSecondMagicNumber = SLOTS;
        this.cardsToPreview = new ReleasePower();
}


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new IncreaseMaxOrbAction(this.defaultSecondMagicNumber));
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new ChannelAction(new theAncient.orbs.Pestilence()));
        }
        this.addToBot(new MakeTempCardInHandAction(new ReleasePower(), 1));
    }




    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_ORBS);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_SLOTS);
            initializeDescription();
        }
    }
}
