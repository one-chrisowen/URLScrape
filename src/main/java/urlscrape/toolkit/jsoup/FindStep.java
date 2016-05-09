package urlscrape.toolkit.jsoup;

public class FindStep {
	
	private FindType type;
	private String key;

	public FindStep(FindType findType, String key) {
		this.type = findType;
		this.key = key;
	}
	
	public FindType getFindType() {
		return type;
	}
	
	public String getKey() {
		return key;
	}
}
