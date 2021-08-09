package theAncient.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import theAncient.DefaultMod;
import theAncient.characters.TheAncient;
import theAncient.powers.PrestigePower;

import static theAncient.DefaultMod.makeCardPath;

public class HumanShield extends AbstractDynamicCard {



    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(HumanShield.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheAncient.Enums.COLOR_ANCIENT_YELLOW;

    private static final int COST = 1;
    private static final int UPGRADE_REDUCED_COST = 1;

    private static final int PltdArmor = 4;
    private static final int UPGRADE_PLUS_PltdArmor = 1;

    private static final int PRESTIGE = -4;

    // /STAT DECLARATION/


    public HumanShield() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = PltdArmor;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = PRESTIGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new PlatedArmorPower(p, magicNumber), magicNumber));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PrestigePower(p, p, defaultSecondMagicNumber), defaultSecondMagicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_PltdArmor);
            initializeDescription();
        }
    }
}