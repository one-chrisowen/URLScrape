package urlscrape.mainApplication;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import urlscrape.dataListeners.IDataListener;
import urlscrape.toolkit.jsoup.JSoupLinkExplorer;

@ContextConfiguration(classes = URLScrape.class, loader = SpringApplicationContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class URLScrapeTest {
	
	private static final String MAINURL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

	private static final String LINKURL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-apricot-ripe---ready-320g.html";

	@Autowired
	private JSoupLinkExplorer explorer;
	
	@Autowired
	private DataBroadcaster broadcaster;
	
	IDataListener mockListener = Mockito.mock(IDataListener.class);
	
	private URLScrape testSubject = null;
	

	@Before
	public void setUp() throws Exception {
		testSubject = new URLScrape(broadcaster, explorer);
		testSubject.addListener(mockListener);
	}
	
	@Test
	public void testGettingLinks() throws IOException {
		
		List<String> links = null;
		
		try {
			links = testSubject.getLinksFromURL(MAINURL);
		} catch (IOException e) {
			Assert.fail("IOException getting links: " + e.getMessage());
		}
		
		assertEquals("Incorrect number of links: ", 7, links.size());
	}
	
	@Test
	public void testGettingTitle() throws IOException {
		
		testSubject.connectTo(LINKURL);
		testSubject.doTitle();
		
		Mockito.verify(mockListener, times(1)).addToObject("title", "Sainsbury's Apricot Ripe & Ready x5 ");
	}
	
	@Test
	public void testGettingDescription() throws IOException {
		
		testSubject.connectTo(LINKURL);
		testSubject.doDescription();
		
		Mockito.verify(mockListener, times(1)).addToObject("description", "Buy Sainsbury's Apricot Ripe & Ready x5 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.");
	}
	
	@Test
	public void testGettingSize() throws IOException {
		
		testSubject.connectTo(LINKURL);
		testSubject.doSize();
		
		Mockito.verify(mockListener, times(1)).addToObject("size", "2.966 Kb");
	}
	
	@Test
	public void testGettingPrice() throws IOException {
		
		testSubject.connectTo(LINKURL);
		testSubject.doPrice();
		
		Mockito.verify(mockListener, times(1)).addToObject("unit_price", "£3.50");
	}

}
