package crawl;

public interface AbstractCrawler {
	void crawl(String url);
	String getInitialUrl(String keyword);
	String getUrl(String keyword,int i);

}
