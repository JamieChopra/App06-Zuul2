/**
 * Class for Sword Item
 */
public class Sword extends Items
{

    public Sword(String thisName, Room thisRoom, Player myPlay) {
        super(thisName, thisRoom);
        swordDamage(myPlay);
    }

    /**
     * When holding the sword it equips it
     */
    public void swordDamage(Player swordPlayer)
    {
        if(isHolding)
        {
            swordPlayer.equipSword();
        }
    }
}
