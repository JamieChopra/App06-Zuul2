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

    public int setEnergy()
    {
        energy = 100;
        return energy;
    }

    public int useEnergy(int usingEnergy)
    {
        energy -= usingEnergy;
        return energy;
    }

    public int restoreEnergy(int restoringEnergy)
    {
        energy += restoringEnergy;
        return energy;
    }

    public int restoreHealth(int restoringHealth)
    {
        health += restoringHealth;
        return health;
    }

     public void shieldHealth()
     {
         if(hasArmour)
         {
             System.out.println("Has it");
             setHealth(200);
         }
         else
         {
             System.out.println("Doesn't have it");
             setHealth(100);
         }
     }

    public void swordDMG()
    {
        if(hasSword)
        {
            System.out.println("Has it");
            setDamage(35);
        }
        else
        {
            System.out.println("Doesn't have it");
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
