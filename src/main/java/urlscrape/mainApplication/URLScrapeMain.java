package urlscrape.mainApplication;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import urlscrape.dataListeners.JSONBuilder;

/**
 * The main application class. This specifies the root
 * component for determining the Spring Boot configuration.
 * 
 * @author Chris
 *
 */
@Component
public class URLScrapeMain {
	
	public static String jsonOutput;
	private static final String URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
	
    public static void main(String[] args) throws Exception {
    	
        org.springframework.context.annotation.AnnotationConfigApplicationContext context = null;

		try {
            context = new AnnotationConfigApplicationContext(URLScrape.class);
            final URLScrape urlScrape = context.getBean(URLScrape.class);
            JSONBuilder jsonBuilder = new JSONBuilder();
            urlScrape.addListener(jsonBuilder);
            doScrape(urlScrape);
            jsonOutput = jsonBuilder.toString();
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            if (context != null) {
                context.close();
            }
            System.out.println("\n\n\n" + jsonOutput);
        }
    }


	public static void doScrape(final URLScrape urlScrape) throws IOException, Exception {
		
		List<String> links = urlScrape.getLinksFromURL(URL);
		
		urlScrape.startList("results");
		
		for (String link : links) {
			urlScrape.connectTo(link);
			urlScrape.doTitle();
			urlScrape.doSize();
			urlScrape.doPrice();
			urlScrape.doDescription();
		}
		urlScrape.endList();
		urlScrape.doTotalPrice();
	}
	
	public static String getJSONOutput() throws Exception {
		return jsonOutput;
	}
}
