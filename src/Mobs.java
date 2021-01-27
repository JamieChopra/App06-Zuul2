public class Mobs extends Entities
{
    String name;
    Room mobsRoom;

    public Mobs(int myHealth, int myDamage)
    {
        super(myHealth, myDamage);
    }

    public String setName(String mobName)
    {
        name = mobName;
        return name;
    }

    public Room setRoom(Room mobRoom)
    {
        mobsRoom = mobRoom;
        return mobsRoom;
    }
}
