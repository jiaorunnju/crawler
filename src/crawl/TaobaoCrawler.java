package crawl;

import java.util.ArrayList;

import crawlmanager.AbstractManager;
import datautil.GoodsInfo;
import java.util.Date;

public class TaobaoCrawler implements AbstractCrawler{
	
	private AbstractManager manager;
	
	private void notify(ArrayList<String> arr){
		manager.update(arr);
	}
	
	public TaobaoCrawler(AbstractManager manager){
		this.manager = manager;
	}
	
	public void crawl(String url){
		ArrayList<String> arr = new ArrayList<>();
		ArrayList<GoodsInfo> data = new ArrayList<>();
		
		System.out.println("Crawling "+url);
		arr.add(url+new Date().toString());
		
		manager.store(data);
		notify(arr);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
