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
	private static final String seller = "当当";
	
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
                    String productPrice = price.html().replaceAll("&yen;", "").trim();
                    Elements title = li.select("p[class=name]>a");
                    String productName = title.attr("title").replaceAll("】", "").
                    		replaceAll("【", "").trim();
                    String producturl = title.attr("href").trim();
                    if((productName != null && productPrice != null)&&
                    		(productName != "" && productPrice != "")){
                    	try{
                    		double p = Double.parseDouble(productPrice);
                    		data.add(new GoodsInfo(producturl, p
                        			, DDCrawler.seller, productName.substring(0, 
                        					(productName.length()>40)?40:productName.length()-1)));
                    	}catch(Exception e){
                    		continue;
                    	}
                    }
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
