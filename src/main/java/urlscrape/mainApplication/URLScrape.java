package urlscrape.mainApplication;

import static urlscrape.toolkit.jsoup.FindType.CLASS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import urlscrape.dataListeners.IDataListener;
import urlscrape.toolkit.jsoup.FindStep;
import urlscrape.toolkit.jsoup.JSoupLinkExplorer;

/**
 * The "business" class, incharge of the main work of
 * scraping the web page and passing data over to the 
 * "broadcaster" component.
 * 
 * @author Chris
 *
 */
@ComponentScan(basePackages = "urlscrape")
public class URLScrape {
	
	public static final String LINK_MATCH_STRING = "Scrape/sainsburys";
	private DataBroadcaster broadcaster;
	private JSoupLinkExplorer explorer;
	
	private Document doc;
	
	private float totalPrice = 0.00F;
	
	
	@Autowired
    public URLScrape(DataBroadcaster broadcaster, JSoupLinkExplorer explorer) {
		this.broadcaster = broadcaster;
		this.explorer = explorer;
	}

	public void connectTo(String url) throws IOException {
		
		Connection connection = Jsoup.connect(url);
		doc = connection.get();
	}
	
	public List<String> getLinksFromURL(String url) throws IOException {
		
        connectTo(url);
        
        Elements links = explorer.getFilteredLinks(doc, LINK_MATCH_STRING);
        if (links == null || links.isEmpty()) {
        	return null;
        }
        
        return linksAsStrings(links);
	}


	private List<String> linksAsStrings(Elements links) {
		List<String> linkHrefs = new ArrayList<String>();
		for (Element link : links) {
			linkHrefs.add(link.attr("abs:href"));
		}
		
		return linkHrefs;
	}

	public void startList(String name) {
		broadcaster.addList(name);
	}

	public void doTitle() {
		String fullTitle = explorer.getTitle(doc);
		String title = fullTitle.split("\\|")[0];
		broadcaster.addObjectToList();
		broadcaster.addToObject("title", title);		
	}

	public void doSize() {
		int bytes = explorer.getSizeOf(doc);
		float kB = ((float) bytes) / 1000;
		String size = String.format("%s %s", kB, "Kb");
		broadcaster.addToObject("size", size);
	}

	public void doDescription() {
		String description = explorer.getMetavalue(doc, "description");
		broadcaster.addToObject("description", description);
		broadcaster.endObject();
	}
	
	public void doPrice() {
		List<FindStep> findSteps = new ArrayList<FindStep>();
		findSteps.add(new FindStep(CLASS, "productContent"));
		findSteps.add(new FindStep(CLASS, "pricePerUnit"));
		
		Element priceElement = explorer.getElementProgressively(doc, findSteps);
		String printablePrice = priceElement.ownText().trim();
		String moneyString = printablePrice.substring(1);
		totalPrice += Float.parseFloat(moneyString);
		
		broadcaster.addToObject("unit_price", printablePrice);
	}
	
	public void addListener(IDataListener listener) {
		broadcaster.addListener(listener);
	}

	public void endList() {
		broadcaster.endList();
	}

	public void doTotalPrice() {
		String formattedPrice = String.format("£%.2f", totalPrice);
		broadcaster.addToObject("total_price", formattedPrice);
	}

}
