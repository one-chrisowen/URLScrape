package urlscrape.dataListeners;

/**
 * Interface to separate handling found data from
 * the finding of data. It should be possible to
 * implement this with other builders / writers, 
 * e.g. to build XML.
 * 
 * @author Chris
 *
 */
public interface IDataListener {

	public void addObjectToList();
	
	public void addListToObject(String name);
	
	public void addToObject(String name, String value);

	public void addToList(String value);
	
	public void endObject();
	
	public void endList();
}
