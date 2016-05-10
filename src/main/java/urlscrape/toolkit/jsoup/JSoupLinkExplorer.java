package urlscrape.toolkit.jsoup;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * Utility class that is specific to JSoup, for abstracting
 * some of the nitty-gritty logic associated with JSoup HTML
 * scanning.
 * 
 * @author Chris
 *
 */
@Component
public class JSoupLinkExplorer {
	
	public Elements getFilteredLinks(Document doc, String filter) {
		
		Elements links = getLinks(doc);
		
		if (StringUtils.isBlank(filter)) {
			return links;
		}
		
		for (int i = links.size()-1; i >= 0; i--) {
			Element link = links.get(i);
			if (link.attr("abs:href").contains(filter)) {
				continue;
			}
			links.remove(link);
		}
		
		return links;
	}
	
	private Elements getLinks(Document doc) {
		Elements links = doc.select("a[href]");
		return links;
	}
	
	public String getMetavalue(Document doc, String string) {
		Elements metaElement = doc.select("meta[name=" + string + "]");
		return (metaElement != null) ? metaElement.attr("content") : "";
	}

	public String getTitle(Document link) {
		return link.title();
	}

	public int getSizeOf(Document link) {
		return link.text().length();
	}

	public Element getElementProgressively(Element baseElement, List<FindStep> steps) {
		
		Elements elements = null;
		
		for (FindStep step : steps) {
			String key = step.getKey();
			switch (step.getFindType()) {
				case CLASS:
					elements = baseElement.getElementsByClass(key);
					baseElement = elements.get(0);
					break;
				case ATTR:
					// For later...
				case TAG:
					// For later...
			}
		}
		
		if (elements == null) {
			return null;
		}
		
		return elements.get(0);
		
	}
}
