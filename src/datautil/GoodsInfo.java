package datautil;

public class GoodsInfo {
	private String name;
	private String url;
	private double price;
	private String seller;
	private String des;
	
	public String getName() {
		return name;
	}
	
	public String getDes() {
		return des;
	}

	public String getUrl() {
		return url;
	}

	public double getPrice() {
		return price;
	}

	public String getSeller() {
		return seller;
	}

	public GoodsInfo(String name,String url,double price,String seller,String des){
		this.name = name;
		this.url = url;
		this.price = price;
		this.seller = seller;
		this.des = des;
	}
}
