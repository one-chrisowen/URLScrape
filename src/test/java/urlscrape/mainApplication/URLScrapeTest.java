package urlscrape.mainApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import urlscrape.toolkit.jsoup.JSoupLinkExplorer;

@ContextConfiguration(classes = URLScrape.class, loader = SpringApplicationContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class URLScrapeTest {
	
	@Autowired
	private JSoupLinkExplorer explorer;
	
	@Autowired
	private DataBroadcaster broadcaster;
	
	private URLScrape testSubject = null;
	
	private String url = null;

	@Before
	public void setUp() throws Exception {
		testSubject = new URLScrape(broadcaster, explorer);
		url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
	}
	
	@Test
	public void testGettingLinks() throws IOException {
		
		Elements links = null;
		
		try {
			links = testSubject.getLinksFromURL(url);
		} catch (IOException e) {
			Assert.fail("IOException getting links: " + e.getMessage());
		}
		
		assertEquals("Incorrect number of links: ", 5, links.size());
	}
	

}
