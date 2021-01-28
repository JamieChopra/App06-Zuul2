/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * Modified and extended by Derek and Andrei
 */
import java.util.ArrayList;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    public ArrayList<Mobs> mobList;
    public ArrayList<Room> roomList;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        roomList = new ArrayList<>();
        mobList = new ArrayList<>();
        createRooms();
        createMobs();
        createPlayer();
        createItems();
        play();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        
        while (! finished) 
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private ArrayList<Room> createRooms()
    {
        Room outside, theater, pub, lab, office;
        Room cave, banditLair, groggsLair, groggRoom, groggTreasure, temple, discipleRoom, forgerRoom, bossRoom, romanRoom, femtoLair;


//        // create the rooms
//        outside = new Room("outside the main entrance of the university");
//        theater = new Room("in a lecture theater");
//        pub = new Room("in the campus pub");
//        lab = new Room("in a computing lab");
//        office = new Room("in the computing admin office");

        cave = new Room("in a cave dwelling");
        banditLair = new Room("in a bandits cave");
        groggsLair = new Room("in a room containing a foul odour with bones littered around");
        groggRoom = new Room("in Grogg: the king of trolls lair");
        groggTreasure = new Room("in Groggs treasure room");
        temple = new Room("outside a temple");
        discipleRoom = new Room("in the temple");
        forgerRoom = new Room("in Samithius: the god forgers workshop");
        bossRoom = new Room("standing before Femto: the eternal");
        romanRoom = new Room("in Roman: the head disciples tower");
        femtoLair = new Room("in the room used to summon Femto: the eternal");

        roomList.add(cave);
        roomList.add(banditLair);
        roomList.add(groggsLair);
        roomList.add(groggRoom);
        roomList.add(groggTreasure);
        roomList.add(temple);
        roomList.add(discipleRoom);
        roomList.add(forgerRoom);
        roomList.add(bossRoom);
        roomList.add(romanRoom);
        roomList.add(femtoLair);

        cave.setExit("north", banditLair);

        banditLair.setExit("west", groggsLair);
        banditLair.setExit("east", temple);
        banditLair.setExit("south", cave);

        temple.setExit("north", discipleRoom);
        temple.setExit("west", banditLair);

        discipleRoom.setExit("north", forgerRoom);
        discipleRoom.setExit("east", romanRoom);
        discipleRoom.setExit("south", temple);

        romanRoom.setExit("north", femtoLair);
        romanRoom.setExit("south", discipleRoom);

        femtoLair.setExit("south", romanRoom);

        forgerRoom.setExit("south", discipleRoom);
        forgerRoom.setExit("west", bossRoom);

        bossRoom.setExit("south", forgerRoom);

        groggsLair.setExit("south", banditLair);
        groggsLair.setExit("north", groggRoom);

        groggRoom.setExit("south", groggsLair);
        groggRoom.setExit("west", groggTreasure);

        groggTreasure.setExit("south", groggRoom);

//        // initialise room exits
//        outside.setExit("east", theater);
//        outside.setExit("south", lab);
//        outside.setExit("west", pub);
//
//        theater.setExit("west", outside);
//
//        pub.setExit("east", outside);
//
//        lab.setExit("north", outside);
//        lab.setExit("east", office);
//
//        office.setExit("west", lab);

        currentRoom = cave;  // start game outside

        return roomList;
    }

    /**
     * Method for creating a list with mobs health, damage, name and room
     */
    private ArrayList<Mobs> createMobs()
    {
        mobList.add( new Mobs(35,10));
        mobList.add( new Mobs(100, 20));
        mobList.add( new Mobs(50, 15));
        mobList.add( new Mobs(120, 40));
        //Nerf SAMITHIUS
        mobList.add( new Mobs(150, 35));
        mobList.add( new Mobs(500, 60));

        mobList.get(0).setName("Looting Bandits");
        mobList.get(1).setName("Grogg: King of Trolls");
        mobList.get(2).setName("Femtos Disciples");
        mobList.get(3).setName("Roman: The Head Disciple");
        mobList.get(4).setName("Samithius: The God Forger");
        mobList.get(5).setName("Femto: The Eternal");

        //Setting mobs to rooms
        mobList.get(0).setRoom(roomList.get(1));
        mobList.get(1).setRoom(roomList.get(3));
        mobList.get(2).setRoom(roomList.get(6));
        mobList.get(3).setRoom(roomList.get(9));
        mobList.get(4).setRoom(roomList.get(7));
        mobList.get(5).setRoom(roomList.get(8));

        return mobList;
    }

    Player myPlayer;

    public Player createPlayer()
    {
        myPlayer = new Player(100, 10);
        return myPlayer;
    }

    Armour myArmour;
    Sword mySword;
    Dragonsbane myDrag;
    ArrayList<Items> itemList = new ArrayList<>();


    public ArrayList<Items> createItems()
    {
        myArmour = new Armour( "Armour",roomList.get(4) , myPlayer);

        mySword = new Sword("Sword", roomList.get(0), myPlayer);

        myDrag = new Dragonsbane("Dragonsbane", roomList.get(10), myPlayer);
        itemList.add(myArmour);
        itemList.add(mySword);
        itemList.add(myDrag);
        return itemList;
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the kingdom of Zorb");
        System.out.println("A cult nearby has recently Awakened a forgotten dragon from its slumber in hopes to destroy the kingdom.");
        System.out.println("You awake to find yourself in a cave, the only person able to stop this creature from following through with its destructive plans");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) 
        {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
            case SEARCH:
                searchRoom(currentRoom);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            roomCheck();
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private void searchRoom(Room myRoom)
    {
        boolean check = false;
        for(int i=0; i<itemList.size(); i++) {
            if (myRoom == itemList.get(i).itemRoom) {
                if (itemList.get(i).name.equals("Armour")) {
                    myPlayer.shieldHealth();

                    System.out.println("You have found armour");
                } else if (itemList.get(i).name.equals("Sword")) {
                    myPlayer.swordDMG();
                    System.out.println("You have found a sword");
                } else if (itemList.get(i).name.equals("Dragonsbane")) {
                    myPlayer.equipBane();
                    System.out.println("You have found Dragonsbane");
                }
                check = true;
                itemList.remove(i);
            }
        }
        if(!check)
        {
            System.out.println("There is nothing in this room");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }


    private void roomCheck()
    {
        for(int i = 0; i < mobList.size() ;i++)
        {
            if(currentRoom == mobList.get(i).mobsRoom)
            {
                System.out.println("You have entered combat with " + mobList.get(i).name);
                Combat myComb = new Combat(mobList.get(i), myPlayer, myDrag);
                if(mobList.get(i).health <= 0)
                    {
                        System.out.println("You have defeated " + mobList.get(i).name);
                        mobList.remove(i);
                        myPlayer.shieldHealth();
                        myPlayer.setEnergy();
                        myPlayer.addScore();
                        System.out.println("Your current score is: " + myPlayer.score);
                    }
                else if(myPlayer.health <= 0)
                {
                    System.out.println("You have been slain.");
                    System.exit(3);
                }
            }
        }

    }
}
