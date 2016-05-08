package urlscrape.dataListeners;

public interface IDataListener {

	public void addObject(String objectID);
	
	public void addList(String listID);
	
	public void addToObject(String name, String value);

	public void addToList(String listID, String value);
	
	public void endCurrentObject();
	
	public void endCurrentList();
}
