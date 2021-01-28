public class Sword extends Items
{

    public Sword(String thisName, Room thisRoom, Player myPlay) {
        super(thisName, thisRoom);
        playerHolding();
        swordDamage(myPlay);
    }

    public void swordDamage(Player swordPlayer)
    {
        if(isHolding)
        {
            swordPlayer.equipSword();
        }
    }
}
