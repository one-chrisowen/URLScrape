package urlscrape.dataListeners;

import urlscrape.mainApplication.URLScrapeMain;

/**
 * Homebrew JSONBuilder to address the simple requirements
 * of the task in hand. GSon, org.json.JSONBuilder etc
 * do a better job but they tend to throw away the ordering
 * as they are based on HashMaps.
 * 
 * @author Chris
 *
 */
public class JSONBuilder implements IDataListener {
	
	private static final String LF = "\n";
	private static final String QUOTE = "\"";
	StringBuilder jsonStringBuilder = new StringBuilder("{");
	private boolean closed = false;
	
	@Override
	public String toString() {
		if (!closed) {
			jsonStringBuilder.append("}");
		}
		closed = true;
		String jsonString = jsonStringBuilder.toString();
		jsonString = jsonString
				.replace(",}","}")
				.replace(",]", "]")
				.replace(",\n}","\n}")
				.replace(",\n]", "\n]");
		
		return jsonString;
		
	}
	
	@Override
	public void addObjectToList() {
		jsonStringBuilder.append(LF);
		jsonStringBuilder.append("{");
		jsonStringBuilder.append(LF);
	}

	@Override
	public void addListToObject(String name) {
		jsonStringBuilder.append(LF);
		jsonStringBuilder.append(QUOTE);
		jsonStringBuilder.append(name);
		jsonStringBuilder.append(QUOTE);
		jsonStringBuilder.append(" : [");
		jsonStringBuilder.append(LF);
	}
	
	@Override
	public void addToObject(String name, String value) {
		jsonStringBuilder.append(QUOTE);
		jsonStringBuilder.append(name);
		jsonStringBuilder.append(QUOTE);
		jsonStringBuilder.append(" : ");
		jsonStringBuilder.append(QUOTE);
		jsonStringBuilder.append(value);
		jsonStringBuilder.append(QUOTE);
		jsonStringBuilder.append(",");
		jsonStringBuilder.append(LF);
	}

	@Override
	public void addToList(String value) {
		jsonStringBuilder.append(QUOTE);
		jsonStringBuilder.append(value);
		jsonStringBuilder.append(QUOTE);
		jsonStringBuilder.append(",");
		jsonStringBuilder.append(LF);
	}

	@Override
	public void endObject() {
		jsonStringBuilder.append("},");
	}

	@Override
	public void endList() {
		jsonStringBuilder.append("],");
	}

}
