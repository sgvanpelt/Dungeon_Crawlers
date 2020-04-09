
/**
 * Class to represent items found in the dungeon.
 * 
 * @author Bas van Pelt - A1
 */
public class Item {
	
	// IVs
	
	/** the item name */
 	private String name;
 	
 	/** the usage text for this item */
	private String usageText;
	
    /**
     * Constructor
     * @param _name the item's name
     * @param _usageText the item's usage text
     */
	public Item(String _name, String _usageText) {
		name = _name;
		usageText = _usageText;
	}
	
	/** getter-methods for IVs */
	public String getName() {
		return name;
	}

	public String getUsageText() {
		return usageText;
	}
	
}
