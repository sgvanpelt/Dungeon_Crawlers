
/**
 * Class describing the player and their properties
 * @author Bas van Pelt - A1
 */
public class Player {
	
	// IVs
	
	/** The current room the player is in */
	private Room CurrentRoom;
	
	/** The room the player started in */
	private Room startRoom;
	
	/** The player's inventory */
	private Inventory inventory;
	
    /** 
    *  Constructor
    *  @param mapEntry the starting room
    */
	public Player(Room mapEntry) {
		CurrentRoom = mapEntry;
		
		// Save room as start room (for win condition)
		startRoom = mapEntry;
		inventory = new Inventory();
	}
	
	/**
	* Check if the direction can take us to another room. If so: do it!
	* 
	* @param direction the direction to go to
	*/
	public void go(String direction) {
		
		// Get the adjacent room(s) from the current room
		Room[] adjacentRooms = CurrentRoom.getAdjacentRooms();
		
 		switch(direction.toLowerCase()) {
 		
 		// Left = index 0
		case "left":
			if(adjacentRooms[0] != null) {
				CurrentRoom = adjacentRooms[0];
			}
			else
				System.out.println("You can't go that way!");
				break;
				
        // Forward = Index 1
        case "forward":
            if (adjacentRooms[1] != null) {
                CurrentRoom = adjacentRooms[1];
            }
            else {
                System.out.println("You can't go that way!");
            }
                break;

         // Right = Index 2
         case "right":
            if (adjacentRooms[2] != null) {
                CurrentRoom = adjacentRooms[2];
                }
            else {
                System.out.println("You can't go that way!");
            }
                break;

        // Back = Index 3
        case "back":
            if (adjacentRooms[3] != null) {
                    CurrentRoom = adjacentRooms[3];
                }
            else {
                System.out.println("You can't go that way!");
            }
                break;
			
		default:
			System.out.println("Direction is invalid!");
		}
	}
	
	/**
	* Method to try to use an item from either the player's inventory or the current room
	* 
	* @param name the name of the item you're trying to use
	*/
	public void use(String name) {
		
        // Try and get the item from the inventory
        Item item = inventory.getItem(name);

        // If item != null -> item came back from inventory successfully
        if (item != null) {
        	
            // "Use" it
        	System.out.println(item.getUsageText());
        }
        
        // else check if the item is in the room
        else if(CurrentRoom.getItem(name) != null) {
        	
            // Get the actual item
            Item roomItem = CurrentRoom.getItem(name);

            // "Use" it
            System.out.println(roomItem.getUsageText());
        }
        
        else {
        	
            System.out.println(name + " doesn't exist.");
            
        }
    }
	
	/**
	* Method to drop an item from the player's inventory
	* 
	* @param name
	*/
	public void drop(String name) {	
		
		// Check if inventory contains the item that u want to drop
        if(inventory.contains(name)) {
        	
        	// Drop the item from your inventory -> it gets removed from your inventory
            Item droppedItem = inventory.drop(name);
            
            // Add the item you just dropped to the current room that you are in
            CurrentRoom.addItem(droppedItem);
            
            System.out.println("You dropped " + droppedItem.getName());
        }
        
        else {
            System.out.println(name + " is not in your inventory.");
        }
        
    }
	
	/**
	* Method to pick up an item from the current room
	* 
	* @param name
	*/
	public void get(String name) {
		
		// Check if rooms contains item
		if(CurrentRoom.contains(name)) {
			
			// Try to take item from room -> since you TAKE it, it gets removed from room's item list!
			Item roomItem = takeItem(name);
			
			// Check if item was taken
			if(roomItem != null) {
				
				// Add it to inventory
				inventory.add(roomItem);
				
				System.out.println("You picked up " + roomItem.getName());
			}
		}
		
        else {      	
            System.out.println(name + " doesn't exist.");     
        }
		
	}
	
	/**
	* Method to TAKE an item from the current room
	* 
	* @param name the name of the requested item
	* 
	* @return Item the requested item
	*/
	private Item takeItem(String name) {
        
		// Check if the item exists in the room that you are in and take it
        Item item = CurrentRoom.getItem(name);
        
        // Check if item was taken
        if(item != null) {
        	
        	// Remove item from the current room that you are in
            CurrentRoom.removeItem(item);
            return item;
        }
        
        return null;
    }
	
	/**
	*  Method to look at the content of the current room
	*/
	public void look() {
		
        // Get string of room items
        String items = CurrentRoom.itemsToString();

        // Items: Magic_Orb, Sword, Rock, Coin, Lantern
        if(items != "") {
        	System.out.println("Items: " + items);
        }
        
        // If not print alternative message
        else {
        	System.out.println("This room contains no items.");
        }

        // Put an extra line inbetween
        System.out.println();
        
        // Get the adjacent room(s) from the current room that you are in
        String exits = CurrentRoom.adjacentRoomsToString();
        
        // Does not need a check since you always have atleast one door in a room
        // Exits: Left | Forward | Right | Back
        System.out.println("Exits: " + exits);
    }
	
	/**
	*  Method to print out a help list
	*/
	public void help() {
		
		// Print out a list of commands and possible methods of using them
		System.out.println("These are the commands u can use in the game: ");
		System.out.println("'Go' + 'direction' (left-forward-right-back): lets u move from room to room.");
		System.out.println("'Get' + 'item name' lets u take the item, puts it in your backpack and removes it from the room.");
		System.out.println("'Drop' +' item name' lets u drop an item from your backpack and puts it in the current room.");
		System.out.println("'Use' + 'item name' lets u use the item that is in the current room or in your backpack");
		System.out.println("'Pack' shows u a list of every item in your inventory.");
		System.out.println("'Help' shows a list of commands.");
		System.out.println("'Look' shows u a list of items and exits in the current room.");
		System.out.println("'Quit' quits the game.");
		
	}
	
	/**
	*  Method to print out the content of your inventory
	*/ 
	public void pack() {
		
		// Get string of inventory items
		String items = inventory.inventoryToString();
		
		 // If item != null -> item came back from inventory successfully
		if(items != "") {
			System.out.println("Inventory: " + items);
		}
		else {
			System.out.println("Your inventory is empty.");
		}
	}
	
	/**
	* Method to check if the win condition is met
	*
	* @param itemName the name of the item used for the win condition check
	* 
	* @return true, if the winning item is used in the start room.
	*/
	public boolean checkWinCondition(String itemName) {
		
		// Check if inventory contains the item that u want check
		Item item = inventory.getItem(itemName);
		
		if(item != null) {
			// If the item u checked equals "Magic_Orb" and you are in the startRoom, return true
			return (item.getName().equals("Magic_Orb") && CurrentRoom == startRoom);
		}
		
		return false;
	}

	/** getter-methods for the class' properties */
	public Room getCurrentRoom() {
		return CurrentRoom;
	}
	
}
