package urlscrape.mainApplication;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import urlscrape.toolkit.jsoup.JSoupLinkExplorer;

@ComponentScan(basePackages = "urlscrape")
public class URLScrape {
	
	public static final String LINK_MATCH_STRING = "Scrape/sainsburys";
	private DataBroadcaster broadcaster;
	private JSoupLinkExplorer explorer;
	
	
	@Autowired
    public URLScrape(DataBroadcaster broadcaster, JSoupLinkExplorer explorer) {
		this.broadcaster = broadcaster;
		this.explorer = explorer;
	}

	public Elements getLinksFromURL(String url) throws IOException {
		
        Connection connection = Jsoup.connect(url);
		Document doc = connection.get();
        
        Elements links = explorer.getFilteredLinks(doc, LINK_MATCH_STRING);
        if (links == null || links.isEmpty()) {
        	return null;
        }
        
        return links;
	}


	public String getResultsAsJSON() throws Exception {
		throw new Exception("Not implemented yet...");
	}

}
