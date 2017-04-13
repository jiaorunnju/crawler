package datautil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoodsInfo {
	private String url;
	private String id;
	private double price;
	private String seller;
	private String des;
	private String time;
	static SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
	
	public String getID(){
		return id;
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
	
	public String getTime(){
		return time;
	}

	public GoodsInfo(String url,double price,String seller,String des){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(url.getBytes());
			this.id = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Error when MD5");
		}
		this.url = url;
		this.price = price;
		this.seller = seller;
		this.des = des;
		Date date = new Date();
		this.time = fomat.format(date);
	}
	
	public String toString(){
		return "ID:\t"+this.id+"\nDes:\t"+this.des+"\nPrice:\t"+this.price
				+"\nSeller:\t"+this.seller+"\nUrl:\t"+this.url
				+"\ntime:\t"+this.time+"\n";
	}
}
