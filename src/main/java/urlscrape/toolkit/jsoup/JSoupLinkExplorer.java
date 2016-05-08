package urlscrape.toolkit.jsoup;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

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
}
