package urlscrape.toolkit.jsoup;

/**
 * A "utility object for representing the parameters
 * to apply successively when drilling down into an
 * HTML document.
 * 
 * @author Chris
 *
 */
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
