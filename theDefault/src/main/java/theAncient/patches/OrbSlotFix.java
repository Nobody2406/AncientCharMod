package theAncient.patches;

import basemod.patches.com.megacrit.cardcrawl.characters.AbstractPlayer.GiveOrbSlotOnChannel;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theAncient.characters.TheAncient;

@SpirePatch(
        clz = GiveOrbSlotOnChannel.class,
        method = "Prefix"
)

public class OrbSlotFix
{
    public static SpireReturn<Void> Prefix(AbstractPlayer __instance, AbstractOrb orbToSet)
    {
        return SpireReturn.Return();
    }
}

