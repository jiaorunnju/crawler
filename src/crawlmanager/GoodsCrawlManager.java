package crawlmanager;

import java.util.ArrayList;

import crawl.*;

public class GoodsCrawlManager implements AbstractManager{
	
	private ArrayList<String> urls = new ArrayList<>();
	private int MAXNUMBER = 30;	//页码
	private int index = 0;
	private String keyword;
	private AbstractCrawler a;
	
	public GoodsCrawlManager(int MaxNumber,String keywords,AbstractCrawler a) {
		this.MAXNUMBER = MaxNumber;
		this.keyword = keywords;
		this.a = a;
	}

	@Override
	public void start() {
		if(keyword.equals("") || a == null){
			System.err.println("No keywords specified!");
			return;
		}
		urls.add(a.getInitialUrl(keyword));
		for(int i=1;i<MAXNUMBER;++i){
			urls.add(a.getUrl(keyword, i));
		}
		while(index < urls.size()){
			index++;
			a.crawl(urls.get(index-1));
		}
	}

	@Override
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
