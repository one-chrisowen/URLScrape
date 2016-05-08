package urlscrape.mainApplication;

import java.io.IOException;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = "urlscrape")
public class URLScrape {
	
	void doScrape(String url) throws Exception {
		throw new Exception("Not implemented yet...");
	}

	public String getResultsAsJSON() {
		return "";
	}

}
