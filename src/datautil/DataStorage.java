package datautil;

import java.util.ArrayList;

public class DataStorage {
	
	private static DataStorage d = null;
	private ArrayList<GoodsInfo> buffer = new ArrayList<>();
	private final int BUFFERSIZE = 1000;
	
	public static DataStorage getInstance(){
		if(d == null){
			d = new DataStorage();
		}
		return d;
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
		
	}
}
