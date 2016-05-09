package urlscrape.mainApplication;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class URLScrapeMain {
	
	private static final String URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
	
    public static void main(String[] args) throws Exception {
    	
        org.springframework.context.annotation.AnnotationConfigApplicationContext context = null;

		try {
            context = new AnnotationConfigApplicationContext(URLScrape.class);
            final URLScrape urlScrape = context.getBean(URLScrape.class);
            doScrape(urlScrape);
        } finally {
            if (context != null) {
                context.close();
            }
        }
    }

	private static void doScrape(final URLScrape urlScrape) throws IOException, Exception {
		
		List<String> links = urlScrape.getLinksFromURL(URL);
		
		urlScrape.startList("results");
		
		for (String link : links) {
			urlScrape.connectTo(link);
			urlScrape.doTitle();
			urlScrape.doSize();
			urlScrape.doDescription();
		}
		
		String resultsJSON = urlScrape.getResultsAsJSON();
	}
}
