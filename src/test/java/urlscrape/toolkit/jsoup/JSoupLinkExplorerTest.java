package urlscrape.toolkit.jsoup;

import static org.junit.Assert.assertEquals;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import urlscrape.mainApplication.URLScrape;

public class JSoupLinkExplorerTest {
	
	private JSoupLinkExplorer testSubject;
	private Document doc;

	@Before
	public void setUp() throws Exception {
		Connection connection = 
				Jsoup.connect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
		doc = connection.get();
		testSubject = new JSoupLinkExplorer();
	}

	@Test
	public void testGetFilteredLinks() throws Exception {
        
        Elements links = testSubject.getFilteredLinks(doc, URLScrape.LINK_MATCH_STRING);
        
        assertEquals("Incorrect number of links: ", 5, links.size());
	}

}
