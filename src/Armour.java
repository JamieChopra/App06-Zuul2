public class Armour extends Items
{

    public Armour(String thisName, Room thisRoom, Player myPlayer)
    {
        super(thisName, thisRoom);
        playerHolding();
        armourHealth(myPlayer);
    }

    /**
     * Increases player health, if playerholding health increases by 100
     */
    public void armourHealth(Player armourPlayer)
    {
        if(isHolding)
        {
            armourPlayer.equipArmour();
        }
    }
}
