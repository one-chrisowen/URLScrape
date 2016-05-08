package urlscrape.mainApplication;

import java.io.IOException;

import org.jsoup.select.Elements;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class URLScrapeMain {
	
	private static final String URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
	
    public static void main(String[] args) throws Exception {
    	
        org.springframework.context.annotation.AnnotationConfigApplicationContext context = null;

		try {
            context = new AnnotationConfigApplicationContext(URLScrape.class);
            final URLScrape urlScrape = context.getBean(URLScrape.class);
            Elements links = urlScrape.getLinksFromURL(URL);
            String resultsJSON = urlScrape.getResultsAsJSON();
        } finally {
            if (context != null) {
                context.close();
            }
        }
    }
}
