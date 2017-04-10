package datautil;

public class DataStorage {
	
	private static DataStorage d = null;
	
	public static DataStorage getInstance(){
		if(d == null){
			d = new DataStorage();
		}
		return d;
	}
	
	public synchronized void store(GoodsInfo good){
		
	}
}
