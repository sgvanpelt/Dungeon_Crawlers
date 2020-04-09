import java.util.ArrayList;
	
/**
 * Class describing the rooms building the dungeon.
 * 
 * @author Bas van Pelt - A1
 */
public class Room {
	
	// IVs
	
	/** the description for this room */
    private String description;
    
    /** an array with 4 entries representing the connection to adjacent rooms */
    private Room[] adjacentRooms;
    
    /** the items this room may contain */
    private ArrayList<Item> items = new ArrayList<Item>();
    
    /** 
    *  Constructor
    *  
    *  @param _description The room's description
    */
    public Room(String _description) {
    	description = _description;
        adjacentRooms =  new Room[4];
    }
    
	/**
	* Method to check if the room contains an item.
	* 
	* @param name the item's name
	* @return boolean true, if the room contains the given item.
	*/
    public boolean contains(String name) {
        for(Item item : items)
        {
            if (item.getName().toLowerCase().equals(name)) 
            	return true;
        }

        return false;
    }
    
    /** Method to add an item to the room's list.
     * 
     * @param item the item you want to add.
     */
    public void addItem(Item item) {
    	items.add(item);
    }
    
    /** Method to remove an item from the room's list.
     * 
     * @param item the item to remove from the list
     */
    public void removeItem(Item item) {
    	items.remove(item);
    }
	
	/**
	* Method to check if u get an item that you want from a room
	* 
	* @param name the name of the item you're trying to get
	* @return Item the item (null if none found)
	*/ 
    public Item getItem(String name) {
        
    	// Go over all items
    	for(Item item : items)
        {
    		// Check if the lowercase version of the item's name equals to the input string
            if (item.getName().toLowerCase().equals(name)) {
            	
            	// If yes return said item
            	return item;
            }
        }
    	
    	// Item not found, return null
        return null;
    }

    /** 
    *  Method to generate String entries in a list responding to the possible exits.
    *  
    *  @return ArrayList<String> list of directions
    */
    private ArrayList<String> GetDirections() {
    	
        ArrayList<String> temp = new ArrayList<String>();
        
        // Left
        if (adjacentRooms[0] != null) temp.add("Left");
 
        // Forward
        if (adjacentRooms[1] != null) temp.add("Forward");

        // Right
        if (adjacentRooms[2] != null) temp.add("Right");

        // Back
        if (adjacentRooms[3] != null) temp.add("Back");

        return temp;
    }
    
    /** Method to convert the room's exits to a formatted and descriptive string.
     * 
     *  @return string description
     */
    public String adjacentRoomsToString() {
    	
        String temp = "";

        ArrayList<String> Doors = GetDirections();
        
        // Convert List to Array so u can access the length properly
        // https://stackoverflow.com/questions/5374311/convert-arrayliststring-to-string-array
        String[] doorsArray = new String[Doors.size()];
        doorsArray = Doors.toArray(doorsArray);

        // For loop till the SECOND last element
        for(int i = 0; i < doorsArray.length - 1; i++)
        {
        	// Add the direction + formatting to the string 
            temp += doorsArray[i] + " | ";
        }

        // Add the last entry
        if(doorsArray.length != 0)
        	temp += doorsArray[doorsArray.length - 1];

        // Return result
        return temp;
    }
    
    /** Method to convert the room's items to a formatted and descriptive string.
     * 
     *  @return string description
     */
    public String itemsToString() {
    	
        String temp = "";
        
        // https://stackoverflow.com/questions/5374311/convert-arrayliststring-to-string-array
        Item[] itemArray = new Item[items.size()];
        itemArray = items.toArray(itemArray);

        // For loop till the SECOND last element
        for(int i = 0; i < itemArray.length - 1; i++)
        {
        	// Add the item name + formatting to the string 
            temp += itemArray[i].getName() + " | ";
        }

        // Add the last entry
        if(itemArray.length != 0) {
        	temp += itemArray[itemArray.length - 1].getName();
        }

        // Return result
        return temp;
    }
    
    /** 
     * Method to set the room adjacent in a specified direction.
     * 
     *  @param direction the direction handed in as a string
     *  @param _room the room to be connected
     */
	public void setAdjacentRoom(String direction, Room _room) {
        
        switch(direction.toLowerCase()) {
        
            case "left":
                
                // Set the left exit to the instance handed in
                adjacentRooms[0] = _room;
                break;
                
            case "forward":
                
                // Set the forward exit to the instance handed in
                adjacentRooms[1] = _room;
                break;
                
            case "right":
                
                // Set the right exit to the instance handed in
                adjacentRooms[2] = _room;
                break;
                
            case "back":
                
                // Set the back exit to the instance handed in
                adjacentRooms[3] = _room;
                break;
                
            default:
                System.out.println("Invalid direction input");
                break;
        }
    }

	/** getter-methods for the class' properties */
	public String getDescription() {
		return description;
	}
	
	public Room[] getAdjacentRooms() {
		return adjacentRooms;
	}
	
}
