package theAncient.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.ElectroPower;
import theAncient.DefaultMod;
import theAncient.characters.TheAncient;
import theAncient.orbs.Pestilence;
import theAncient.powers.ContaminatePower;

import static theAncient.DefaultMod.makeCardPath;

public class Contaminate extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Contaminate.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;

    public static final String IMG = makeCardPath("Power.png");// "public static final String IMG = makeCardPath("AttackTemplate.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.POWER;       //
    public static final CardColor COLOR = TheAncient.Enums.COLOR_ANCIENT_YELLOW;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 0;


    // /STAT DECLARATION/


    public Contaminate(){
        super(ID,IMG,COST,TYPE,COLOR,RARITY,TARGET);
     this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
}


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower("ContaminatePower")) {
            this.addToBot(new ApplyPowerAction(p, p, new ContaminatePower(p)));
        }

        for(int i = 0; i < this.magicNumber; ++i) {
            AbstractOrb orb = new Pestilence();
            this.addToBot(new ChannelAction(orb));
        }

    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
