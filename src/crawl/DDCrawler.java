package crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import datautil.Constants;
import datautil.DataStorage;
import datautil.GoodsInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DDCrawler implements AbstractCrawler{
	private DataStorage d = DataStorage.getInstance();
	
	public void crawl(String url){
		ArrayList<GoodsInfo> data = new ArrayList<>();
		
		try {
			Document document = Jsoup.connect(url).timeout(5000).get();
			Elements uls = document.select("ul[class=bigimg cloth_shoplist]");
			Iterator<Element> ulIter = uls.iterator();
			
			while(ulIter.hasNext()){
                Element ul = ulIter.next();
                Elements lis = ul.select("li");
                Iterator<Element> liIter = lis.iterator();
                while(liIter.hasNext()){
                    Element li = liIter.next();
                    Elements price = li.select("p[class=price]>span");
                    String productPrice = price.html().replaceAll("&yen;", "");
                    Elements title = li.select("p[class=name]>a");
                    String productName = title.attr("title");
                    if(productName != null && productPrice != null)
                    	System.out.println(productName+"\t"+productPrice);
                }
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			return;
		}finally{
			store(data);
		}
	}

	@Override
	public String getInitialUrl(String keyword) {
		return Constants.DDURL + keyword;
	}
	
	public void store(ArrayList<GoodsInfo> arr) {
		for (GoodsInfo goodsInfo : arr) {
			this.d.store(goodsInfo);
		}
	}

	@Override
	public String getUrl(String keyword, int i) {
		return Constants.DDURL + keyword + 
				Constants.ACT + Constants.DDPAGE + (i + 1);
	}
}
