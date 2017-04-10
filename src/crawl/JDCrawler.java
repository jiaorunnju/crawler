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

public class JDCrawler implements AbstractCrawler{
private DataStorage d = DataStorage.getInstance();
	
	public void crawl(String url){
		ArrayList<GoodsInfo> data = new ArrayList<>();
		
		try {
			Document document = Jsoup.connect(url).timeout(5000).get();
			Elements uls = document.select("ul[class=gl-warp clearfix]");
			Iterator<Element> ulIter = uls.iterator();
			
			while(ulIter.hasNext()) {  
                Element ul = ulIter.next();  
                Elements lis = ul.select("li[data-sku]");  
                Iterator<Element> liIter = lis.iterator();  
                while(liIter.hasNext()) {  
                    Element li = liIter.next();  
                    Element div = li.select("div[class=gl-i-wrap]").first();  
                    Elements title = div.select("div[class=p-name p-name-type-2]>a");  
                    String productName = title.attr("title");  
                    Elements price = div.select(".p-price>strong");  
                    String productPrice =price.attr("data-price");
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
		return Constants.JDURL + keyword  + Constants.JDENC;
	}
	
	public void store(ArrayList<GoodsInfo> arr) {
		for (GoodsInfo goodsInfo : arr) {
			this.d.store(goodsInfo);
		}
	}

	@Override
	public String getUrl(String keyword, int i) {
		return Constants.JDURL + keyword + 
				Constants.JDENC + Constants.JDPAGE + (i + 1);
	}
}
