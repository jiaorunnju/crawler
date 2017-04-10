package crawlmanager;

import java.util.ArrayList;

import datautil.GoodsInfo;

public interface AbstractManager {
	void start();
	void update(ArrayList<String> arr);
	void store(ArrayList<GoodsInfo> arr);
}
