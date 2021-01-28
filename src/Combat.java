import java.util.Scanner;

public class Combat
{
public int maxDmg = 0;
private Scanner reader;



    public Combat(Mobs enemy, Player play)
        {
            maxDmg = enemy.damage;
            reader = new Scanner(System.in);
            while(enemy.health > 0)
            {
                takeInput(combatChoices(), enemy, play);
                enemyAttack(enemy, play);
                System.out.println("Player health = " + play.health);
                System.out.println("Enemy health = " + enemy.health);
            }
        }

public int enemyAttack(Mobs enemy, Player play)
{
    play.takeDamage(enemy.damage);
    enemy.setDamage(maxDmg);
    return play.health;
}

public int playerAttack(Mobs enemy, Player play)
{
    enemy.takeDamage(play.damage);
    return enemy.health;
}

public int playerDefend(Mobs enemy, Player play)
    {
        enemy.damage /= 2;
        return enemy.damage;
    }


    public String combatChoices()
    {
        System.out.println("Attack");
        System.out.println("Defend");
        System.out.println("Rest");
        System.out.println("Use");
        String combatOption = reader.nextLine();
        return combatOption;

    }

    public void takeInput(String takingInput, Mobs enemy, Player play)
    {
        if(takingInput.equals("Attack"))
        {
            playerAttack(enemy, play);
        }

        else if(takingInput.equals("Defend"))
        {
            playerDefend(enemy, play);
        }

        else if(takingInput.equals("Rest"))
        {

        }

        else if(takingInput.equals("Use"))
        {

        }
    }

}
