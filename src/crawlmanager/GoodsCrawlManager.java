package crawlmanager;

import java.util.ArrayList;

import crawl.TaobaoCrawler;
import datautil.DataStorage;
import datautil.GoodsInfo;

public class GoodsCrawlManager implements AbstractManager{
	
	private ArrayList<String> urls = new ArrayList<>();
	private int MAXNUMBER;
	private int index = 0;
	private DataStorage d;
	
	public GoodsCrawlManager(int MaxNumber,String starturl) {
		this.MAXNUMBER = MaxNumber;
		this.urls.add(starturl);
		d = DataStorage.getInstance();
	}

	@Override
	public void start() {
		while(index < urls.size()){
			index++;
			new TaobaoCrawler(this).crawl(urls.get(index-1));
		}
	}

	@Override
	public synchronized void update(ArrayList<String> arr) {
		if(this.MAXNUMBER - arr.size() > 0){
			this.urls.addAll(arr);
			this.MAXNUMBER -= arr.size();
		}
		
	}

	@Override
	public void store(ArrayList<GoodsInfo> arr) {
		for (GoodsInfo goodsInfo : arr) {
			this.d.store(goodsInfo);
		}
	}

}
