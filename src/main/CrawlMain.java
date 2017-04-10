package main;

import crawlmanager.AbstractManager;
import crawlmanager.GoodsCrawlManager;

public class CrawlMain {
	public static void main(String[] args) {
		AbstractManager manager = new GoodsCrawlManager(10,"http://test/");
		manager.start();
	}
}
