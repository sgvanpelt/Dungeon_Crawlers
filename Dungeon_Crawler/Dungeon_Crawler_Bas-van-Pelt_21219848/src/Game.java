
/**
 * Game class, handles game logic and manages objects.
 * 
 * @author Bas van Pelt - A1
 */
public class Game {
	
	// IVs
	
	/** The player instance */
	private Player m_Player;

	/** Variable to control game loop */
	boolean keepPlaying = true;
	
    /** Constructor */
	public Game() {
		
		/* The map is linked together so we only need the starting room
		   since we're able to navigate from there */
	    Room firstRoom = generateDungeon();
	    
	    // Hand first room over to player constructor
	    m_Player = new Player(firstRoom);
	}
	
    /**
     * Main method for the game containing the game loop.
     */
	public void run() {
		
		// Variable to save last room in
		Room lastRoom = null;
		
		while(keepPlaying) {
			
				// Check if room changed
				if(m_Player.getCurrentRoom() != lastRoom) {
					
					// Display current room description
					display();
					
					// Save current room as new last room
					lastRoom = m_Player.getCurrentRoom();
				}
				
				// Get user input
				String input = ConsoleIO.readInput();
				
				// Handle user input
				handleCommand(input);
		}
		
		// If keepPlaying = false, game has ended
		System.out.println("Game ended");
	}
		
	/**
	* Method to handle logic based on user input. Follows OO-patterns by telling
	* objects to behave in certain ways instead of forcing them.
	* 
	* @param userInput the string entered by the player.
	*/
	private void handleCommand(String userInput) {

		// Check if the input is valid
		if(checkInput(userInput)) {
			
			// Split the user input string.
			String[] inputArray = userInput.split(" ");
			
			switch(inputArray[0].toLowerCase()) {
			
			// At this point the two word commands will be executed
			case "go":
				m_Player.go(inputArray[1].toLowerCase());
				break;
				
			case "use":
				// Check if win condition is met
				if(m_Player.checkWinCondition(inputArray[1])) {
					
					// If yes, end the game
					System.out.println("Congratulations, you beat the game!");
					keepPlaying = false;
				}
				
				// else call the normal use method
				else {
					m_Player.use(inputArray[1].toLowerCase());
				}
				break;	
				
			case "drop":
				m_Player.drop(inputArray[1].toLowerCase());
				break;
				
			case "get":
				m_Player.get(inputArray[1].toLowerCase());
				break;
				
			// At this point the one word commands will be executed
			case "look":
				m_Player.look();
				break;
				
			case "help":
				m_Player.help();
				break;
				
			case "pack":
				m_Player.pack();
				break;
				
			case "quit":
				keepPlaying = false;
				break;
			}
		}
		
		// Miss-input
		else System.out.println("Invalid input");
	}
	
	/**
	* Method to check if the input is valid for the commmands in the game.
	* 
	* @param input the user input to check
	* @return boolean true, if input is a valid command with the required parameters.
	*/
	private boolean checkInput(String input) {
	
		// If input is empty input is invalid
		if(input.length() == 0) {
			return false;
		}
		
		String[] inputArray = input.split(" ");
		String command = inputArray[0].toLowerCase();
		// If it's one of the one word commands
		if(inputArray.length == 1) {
			
			// If it's none of the valid one word commands input is invalid
			if(!(	command.equals("look") ||
					command.equals("help") ||
					command.equals("pack") ||
					command.equals("quit"))) return false;
		}
		
		// Else if length > 2 -> miss-input
		else if(inputArray.length > 2) {
			return false;
		}
		
		// At this point the inputArray has a length of 2, we need to check for valid command
		else {
			if(!(	command.equals("go") ||
					command.equals("use") ||
					command.equals("drop") ||
					command.equals("get"))) return false;
		}
		
		// If this point in the code is reached, the input is valid
		return true;

	}
	
	/** 
	* Method to print the current room description and options 
	*/
	private void display() {
		
		// Get the description of the current room you are in
		String description = m_Player.getCurrentRoom().getDescription();
		
		// Display it in a somewhat neat format
		System.out.println("------------------------------------------");
		System.out.println();
		System.out.println("You are currently in: " + description);
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println("options: go | use | drop | get | look | help | pack | quit");
	}
	
	/**
	* Method to generate the rooms and items of the dungeon
	* 
	* @return Room startRoom
	*/
	private Room generateDungeon() {
        
		// Create rooms
        Room startRoom = new Room("Start Room");
        Room secondRoom = new Room("Second room");
        Room thirdRoom = new Room("Third room");
        Room fourthRoom = new Room("Fourth room");
        Room fifthRoom = new Room("Fifth room");
        Room sixthRoom = new Room("Sixth room");
        Room seventhRoom = new Room("Seventh room");
        Room eighthRoom = new Room("Eigth room");
        Room ninthRoom = new Room("Ninth room");
        Room tenthRoom = new Room("Tenth room");
        Room eleventhRoom = new Room("Eleventh room");
        Room twelfthRoom = new Room("Twelfth room");
        Room thirteenthRoom = new Room("Thirteenth room");
        
        // Connect the rooms with adjacent rooms
        connectRooms(startRoom, "left", secondRoom);
        connectRooms(startRoom, "forward", thirdRoom);
        connectRooms(startRoom, "back", fifthRoom);
        
        connectRooms(thirdRoom, "right", fourthRoom);
        
        connectRooms(fifthRoom, "right", sixthRoom);
        
        connectRooms(sixthRoom, "back", seventhRoom);
        connectRooms(sixthRoom, "right", eighthRoom);
        
        connectRooms(eighthRoom, "forward", ninthRoom);
        connectRooms(eighthRoom, "right", tenthRoom);
        
        connectRooms(eleventhRoom, "left", ninthRoom);
        connectRooms(eleventhRoom, "back", tenthRoom);
        connectRooms(eleventhRoom, "forward", twelfthRoom);
        
        connectRooms(twelfthRoom, "right", thirteenthRoom);
   
        // Create items, one if which is an item that makes you win the game
        Item item1 = new Item("Lantern", "The lantern lights up the room!");
        Item item2 = new Item("Coin", "This ancient gold coin could be worth alot of money!");
        Item item3 = new Item("Rock", "The rock is very heavy, it also does nothing!");
        Item item4 = new Item("Sword", "This sword might come in helpful while fighting enemies!");
		Item winItem = new Item("Magic_Orb", "This orb emits a mysterious energy that can only be explained by magic.\n\n" +
											 "Use this in the start room to beat the game!");
		
		// Add items to different rooms
        secondRoom.addItem(item1);
        fourthRoom.addItem(item2);
        seventhRoom.addItem(item3);
        ninthRoom.addItem(item4);
        thirteenthRoom.addItem(winItem);
        
        // Return first room
        return startRoom;
    }
	
	/**
	* Method to connect the rooms by assigning the appropriate exits to each other
	* 
	* @param _first the first room you want to address
	* @param direction the direction to connect the following room to, handed in as a string
	* @param _second the second room to be connected.
	*/
	private void connectRooms(Room _first, String direction, Room _second) {
		
		switch(direction.toLowerCase()) {
		
		case "left":
			_first.setAdjacentRoom("left", _second);
			_second.setAdjacentRoom("right", _first);
			break;
			
		case "forward":
			_first.setAdjacentRoom("forward", _second);
			_second.setAdjacentRoom("back", _first);
			break;
			
		case "right":
			_first.setAdjacentRoom("right", _second);
			_second.setAdjacentRoom("left", _first);
			break;
			
		case "back":
			_first.setAdjacentRoom("back", _second);
			_second.setAdjacentRoom("forward", _first);
			break;
			
		// Invalid direction input
		default:
            System.out.println("Invalid direction input");
            break;
		}
	}
	
}

