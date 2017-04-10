package main;

import crawlmanager.AbstractManager;
import crawlmanager.GoodsCrawlManager;

public class CrawlMain {
	public static void main(String[] args) {
		AbstractManager ac = new GoodsCrawlManager(5,"书包");
		ac.start();
	}
}
