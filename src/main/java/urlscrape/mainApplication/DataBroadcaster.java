package urlscrape.mainApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import urlscrape.dataListeners.IDataListener;

@Component
public class DataBroadcaster {
	
	HashSet<IDataListener> listeners;
	
	public DataBroadcaster() {
		listeners = new HashSet<>();
	}
	
	public void addListener(IDataListener listener) {
		listeners.add(listener);
	}

	public void addList(String listID) {
		for (IDataListener listener : listeners) {
			listener.addList(listID);
		}		
	}
	
	public void addToList(String listID, String value) {
		for (IDataListener listener : listeners) {
			listener.addToList(listID, value);
		}
	}
	
	public void addObject(String objectID) {
		for (IDataListener listener : listeners) {
			listener.addObject(objectID);
		}
	}
	
	public void addToObject(String name, String value) {
		for (IDataListener listener : listeners) {
			listener.addToObject(name, value);
		}
	}
}
