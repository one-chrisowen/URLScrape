package acceptanceTests.steps;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import urlscrape.mainApplication.DataBroadcaster;
import urlscrape.mainApplication.URLScrape;
import urlscrape.toolkit.jsoup.JSoupLinkExplorer;

@ContextConfiguration(classes = URLScrape.class, loader = SpringApplicationContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class URLScrapeSteps {
	
	private String url;
	private URLScrape urlScrape = new URLScrape(new DataBroadcaster(), new JSoupLinkExplorer());

	@Given("^the URL \"([^\"]*)\"$")
	public void the_URL(String arg1) throws Throwable {
		url = arg1;
	}

	@And("^good connectivity to it$")
	public void good_connectivity_to_it() throws Throwable {
		try {
	        URL connectUrl = new URL(url);
	        HttpURLConnection urlConn = (HttpURLConnection) connectUrl.openConnection();
	        urlConn.connect();
	        assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
	    } catch (IOException e) {
	    	Assert.fail("URL connection failed : " + e.getMessage());
	    }
	}

	@When("^the urlScrape utility is run against that URL$")
	public void the_urlScrape_utility_is_run_against_that_URL() throws Throwable {
		List<String> links = urlScrape.getLinksFromURL(url);
	}

	@Then("^the utility returns an accurate JSON representation of the product data$")
	public void the_utility_returns_an_accurate_JSON_representation_of_the_product_data() throws Throwable {
		String jsonOutput = urlScrape.getResultsAsJSON();
	}


}
