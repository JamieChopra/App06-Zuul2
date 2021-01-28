//Player becomes a sub-class of entities
public class Player extends Entities
{
    int energy = 0;
    int score = 0;
    boolean hasSword = false;
    boolean hasArmour = false;
    boolean hasDragonsbane = false;

    //Super copies constructor from entities
    public Player(int myHealth, int myDamage) {
        super(myHealth, myDamage);
        setEnergy();
        shieldHealth();
        swordDMG();
    }

    /**
     * Sets the base energy for a player
     */
    public int setEnergy()
    {
        energy = 100;
        return energy;
    }

    /**
     * Method for using energy (used in Attack and Defend)
     */
    public int useEnergy(int usingEnergy)
    {
        energy -= usingEnergy;
        return energy;
    }

    /**
     * Method for restoring energy (used in rest)
     */
    public int restoreEnergy(int restoringEnergy)
    {
        energy += restoringEnergy;
        return energy;
    }

    /**
     * Method for restoring health
     */
    public int restoreHealth(int restoringHealth)
    {
        health += restoringHealth;
        return health;
    }

    /**
     * Method that resets health after fighting a mob object (resets to 200 if armour is equip)
     */
     public void shieldHealth()
     {
         if(hasArmour)
         {
             setHealth(200);
         }
         else
         {
             setHealth(100);
         }
     }

    /**
     * Method for increasing damage if sword is equip
     */
    public void swordDMG()
    {
        if(hasSword)
        {
            setDamage(35);
        }
        else
        {
            setDamage(10);
        }
    }


    public int addScore()
    {
        score += 100;
        return score;
    }

    public boolean equipArmour()
    {
        hasArmour = true;
        return hasArmour;
    }

    public boolean equipSword()
    {
        hasSword = true;
        return hasSword;
    }

    public boolean equipBane()
    {
        hasDragonsbane = true;
        return hasDragonsbane;
    }
    public boolean destroyBane()
    {
        hasDragonsbane = false;
        return hasDragonsbane;
    }
}
