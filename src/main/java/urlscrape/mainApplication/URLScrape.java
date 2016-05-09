package urlscrape.mainApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import urlscrape.dataListeners.IDataListener;
import urlscrape.toolkit.jsoup.JSoupLinkExplorer;

@ComponentScan(basePackages = "urlscrape")
public class URLScrape {
	
	public static final String LINK_MATCH_STRING = "Scrape/sainsburys";
	private DataBroadcaster broadcaster;
	private JSoupLinkExplorer explorer;
	
	private Document doc;
	
	
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
	}
	
	public String getResultsAsJSON() throws Exception {
		throw new Exception("Not implemented yet...");
	}

	public void addListener(IDataListener listener) {
		broadcaster.addListener(listener);
	}

}
