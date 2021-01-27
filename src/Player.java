//Player becomes a sub-class of entities
public class Player extends Entities
{
    int energy = 0;
    boolean hasKey = false;
    boolean hasSword = false;
    boolean hasArmour = false;
    boolean hasDragonsbane = false;

    //Super copies constructor from entities
    public Player(int myHealth, int myDamage) {
        super(myHealth, myDamage);
        setEnergy();
    }

    public int setEnergy()
    {
        energy = 100;
        return energy;
    }

    public int useEnergy(int usingEnergy)
    {
        energy = usingEnergy;
        return energy;
    }

    public int restoreEnergy(int restoringEnergy)
    {
        energy = restoringEnergy;
        return energy;
    }
}
