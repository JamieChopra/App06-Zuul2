public class Entities {

    int health = 0;
    int damage = 0;
    boolean alive = true;

    public Entities(int myHealth, int myDamage)
    {
        health = myHealth;
        damage = myDamage;
    }

    public int setHealth(int thisHealth)
    {
        health = thisHealth;
        return health;
    }

    public int setDamage(int thisDamage)
    {
        damage = thisDamage;
        return damage;
    }

    public int takeDamage(int thisTakeDamage)
    {
        health -= thisTakeDamage;
        return damage;
    }

    public boolean isDead()
    {
        alive = false;
        return alive;
    }
}

