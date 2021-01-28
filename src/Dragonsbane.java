/**
 * Dragonsbane item class
 */
public class Dragonsbane extends Items
{

    public Dragonsbane(String thisName, Room thisRoom, Player mPlay) {
        super(thisName, thisRoom);
        playerHolding();
        hasBane(mPlay);
    }

    /**
     * When isholding is true equip the dragonsbane
     */
    public void hasBane(Player banePlay)
    {
        if(isHolding)
        {
            banePlay.equipBane();
        }
    }
}
