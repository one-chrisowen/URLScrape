package acceptanceTests.steps;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import urlscrape.mainApplication.URLScrape;
import urlscrape.mainApplication.URLScrapeMain;

@ContextConfiguration(classes = URLScrape.class, loader = SpringApplicationContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class URLScrapeSteps {

	@Given("^the URL \"([^\"]*)\"$")
	public void the_URL(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^good connectivity to it$")
	public void good_connectivity_to_it() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^the urlScrape utility is run against that URL$")
	public void the_urlScrape_utility_is_run_against_that_URL() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the utility returns an accurate JSON representation of the product data$")
	public void the_utility_returns_an_accurate_JSON_representation_of_the_product_data() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}


}
