package iteration3;

import java.util.ArrayList;

public class Inventory {

	private ArrayList<Item> itemList;

	public ArrayList<Item> getInventory() {
		return itemList;
	}
	public void addItem(Item item){
		itemList.add(item);
	}
	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	
}
