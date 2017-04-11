package crawlmanager;

import java.util.ArrayList;

import crawl.AbstractCrawler;
import crawl.DDCrawler;
import crawl.JDCrawler;

public class GoodsCrawlManager implements AbstractManager{
	
	private ArrayList<String> urls = new ArrayList<>();
	private int MAXNUMBER = 30;	//页码
	private int index = 0;
	private String keyword;
	
	public GoodsCrawlManager(int MaxNumber,String keywords) {
		this.MAXNUMBER = MaxNumber;
		keyword = keywords;
	}
	
	public GoodsCrawlManager(String keywords) {
		this.MAXNUMBER = 30;
		keyword = keywords;
	}
	
	public GoodsCrawlManager(){
		keyword = "";
	}

	@Override
	public void start() {
		if(keyword.equals("")){
			System.err.println("No keywords specified!");
			return;
		}
		AbstractCrawler ac = new JDCrawler();
		urls.add(ac.getInitialUrl(keyword));
		for(int i=1;i<MAXNUMBER;++i){
			urls.add(ac.getUrl(keyword, i));
		}
		while(index < urls.size()){
			index++;
			ac.crawl(urls.get(index-1));
		}
	}

	@Override
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
