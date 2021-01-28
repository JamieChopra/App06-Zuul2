import java.util.Scanner;

public class Combat
{
public int maxDmg = 0;
private Scanner reader;



    public Combat(Mobs enemy, Player play, Dragonsbane db)
        {
            maxDmg = enemy.damage;
            reader = new Scanner(System.in);
            while(enemy.health > 0 && play.health > 0)
            {
                System.out.println("Player health = " + play.health);
                System.out.println("Player energy = " + play.energy);
                System.out.println(enemy.name + " health = " + enemy.health);
                takeInput(combatChoices(), enemy, play, db);
                enemyAttack(enemy, play);
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
        play.restoreHealth(25);
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

    public void takeInput(String takingInput, Mobs enemy, Player play, Dragonsbane db)
    {
        if(takingInput.equals("Attack") && play.energy >= 20)
        {
            playerAttack(enemy, play);
            play.useEnergy(20);
        }

        else if(takingInput.equals("Defend") && play.energy >= 10)
        {
            playerDefend(enemy, play);
            play.useEnergy(10);
        }

        else if(takingInput.equals("Rest"))
        {
            play.setEnergy();
        }

        else if(takingInput.equals("Use"))
        {
            if(enemy.name == "Femto: The Eternal" && db.isHolding)
            {
                enemy.setHealth(enemy.health/5);
                enemy.setDamage(enemy.damage/2);
                play.destroyBane();
            }
            else if(enemy.name != "Femto: The Eternal" && db.isHolding)
            {
                System.out.println("Cannot use an item against this enemy");
                takeInput(combatChoices(), enemy, play, db);
            }
            else if(enemy.name == "Femto: The Eternal")
            {
                System.out.println("No items to use");
                takeInput(combatChoices(), enemy, play, db);
            }
            else
            {
                System.out.println("Cannot do that right now");
                takeInput(combatChoices(), enemy, play, db);
            }

        }
        else
        {
            if(takingInput.equals("Attack") && play.energy < 20)
            {
                System.out.println("You do not have sufficient energy to Attack.");
                System.out.println("You must rest to restore energy.");
            }

            else if(takingInput.equals("Defend") && play.energy < 10)
            {
                System.out.println("You do not have sufficient energy to Defend.");
                System.out.println("You must rest to restore energy.");
            }
            else
            {
                System.out.println("Invalid input, please re-enter a command.");
                takeInput(combatChoices(), enemy, play, db);
            }
        }
    }

}
