package urlscrape.mainApplication;

import java.util.HashSet;

import org.springframework.stereotype.Component;

import urlscrape.dataListeners.IDataListener;

/**
 * A class to do the talking to listeners that have
 * an interest in receiving the scraped data.
 * 
 * @author Chris
 *
 */
@Component
public class DataBroadcaster {
	
	HashSet<IDataListener> listeners;
	
	public DataBroadcaster() {
		listeners = new HashSet<IDataListener>();
	}
	
	public void addListener(IDataListener listener) {
		listeners.add(listener);
	}

	public void addList(String listID) {
		for (IDataListener listener : listeners) {
			listener.addListToObject(listID);
		}		
	}
	
	public void addToList(String value) {
		for (IDataListener listener : listeners) {
			listener.addToList(value);
		}
	}
	
	public void addObjectToList() {
		for (IDataListener listener : listeners) {
			listener.addObjectToList();
		}
	}
	
	public void addToObject(String name, String value) {
		for (IDataListener listener : listeners) {
			listener.addToObject(name, value);
		}
	}

	public void endObject() {
		for (IDataListener listener : listeners) {
			listener.endObject();
		}
	}
	
	public void endList() {
		for (IDataListener listener : listeners) {
			listener.endList();
		}
	}
}
