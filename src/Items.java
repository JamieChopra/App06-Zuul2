public class Items {
    String name;
    boolean isHolding = false;
    Room itemRoom;

    public Items(String thisName, Room thisRoom)
    {
        itemName(thisName);
        thisItemRoom(thisRoom);
    }

    public String itemName(String myName)
    {
        name = myName;
        return name;
    }

    /**
     * Returns whether a player is holding an item.
     */
    public boolean playerHolding()
    {
        isHolding = true;
        return isHolding;
    }

    /**
     * Returns what room an item is in.
     */
    public Room thisItemRoom(Room myRoom)
    {
        itemRoom = myRoom;
        return itemRoom;
    }

    public void hasBane(Player banePlay)
    {
        if(isHolding)
        {
            banePlay.equipBane();
        }
    }
}
