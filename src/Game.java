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

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        createRooms();
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
    private void createRooms()
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
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
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
            System.out.println(currentRoom.getLongDescription());
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
}
