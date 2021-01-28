/**
 * Dragonsbane item class
 */
public class Dragonsbane extends Items
{

    public Dragonsbane(String thisName, Room thisRoom, Player mPlay) {
        super(thisName, thisRoom);
        hasBane(mPlay);
    }

    /**
     * When is holding is true equip the dragonsbane
     */
    public void hasBane(Player banePlay)
    {
        if(isHolding)
        {
            banePlay.equipBane();
        }
    }
}
