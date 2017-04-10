package crawl;

import java.util.ArrayList;

import crawlmanager.AbstractManager;
import datautil.GoodsInfo;

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
		//�������ȡ����
		
		manager.store(data);
		notify(arr);
	}
}
