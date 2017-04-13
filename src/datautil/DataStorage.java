package datautil;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataStorage {
	
	private static DataStorage d = null;
	private ArrayList<GoodsInfo> buffer = new ArrayList<>();
	private final int BUFFERSIZE = 500;
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private String DBURL = "";
	private String DBUSER = "";
	private String DBPASS = "";
	private static final String insertGood = "replace into GOODS (id,des,seller,url) values ";
	private static final String insertPrice = "replace into PRICE (id,date,price) values ";
	
	
	public static DataStorage getInstance(){
		if(d == null){
			d = new DataStorage();
			d.init();
		}
		return d;
	}
	
	public void sync(){
		submit();
	}
	
	public void store(GoodsInfo good){
		synchronized(DataStorage.class){
			if(buffer.size() >= BUFFERSIZE){
				submit();
				buffer.clear();
			}
			if(good != null){
				buffer.add(good);
			}
		}	
	}
	
	private void submit(){ 
		Connection conn = null;
		System.out.println("submit success");
		try {
			Class.forName(DBDRIVER);
			conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
			String sql1 = insertGood;
			for(int i=0;i<buffer.size();++i){
				GoodsInfo gi = buffer.get(i);
				sql1 += "(\""+gi.getID()+"\",\""+gi.getDes()+"\",\""+gi.getSeller()+"\",\""
						+gi.getUrl()+"\")";
				if(i<buffer.size()-1){
					sql1 += ",";
				}else if(i == buffer.size()-1){
					sql1 += ";";
				}
			}
			//System.out.println(sql1);
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			//pstmt1.executeQuery();
			pstmt1.executeUpdate();
			pstmt1.close();
			
			String sql2 = insertPrice;
			for(int i=0;i<buffer.size();++i){
				GoodsInfo gi = buffer.get(i);
				sql2 += "(\""+gi.getID()+"\",\""+gi.getTime()+"\",\""+gi.getPrice()+"\")";
				if(i<buffer.size()-1){
					sql2 += ",";
				}else if(i == buffer.size()-1){
					sql2 += ";";
				}
			}
			
			//System.out.println(sql2);
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			//pstmt2.executeQuery();
			pstmt2.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Error when access Database"+e.getMessage());
			System.exit(1);
		}
	}
	
	public void init(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream("crawl.conf")));
			String dburl="";
			String dbaccount="";
			String dbpasswd="";
			String temp = br.readLine();
			String[] strarr = temp.split(" ");
			dburl = strarr[0];
			dbaccount = strarr[1];
			dbpasswd = strarr[2];
			this.DBURL = dburl;
			this.DBPASS = dbpasswd;
			this.DBUSER = dbaccount;
			br.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}
