package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import crawl.DDCrawler;
import crawl.JDCrawler;
import crawlmanager.AbstractManager;
import crawlmanager.GoodsCrawlManager;

public class CrawlMain {
	static int MAX = 1;
	public static void main(String[] args) {
		if(args.length > 2){
			System.out.println("Usage:\tcrawl FILENAME "
					+ "\nThe Keywords are written in the FILENAME.");
			System.exit(1);
		}
		AbstractManager ac = null;
		BufferedReader br = null;
		ArrayList<String> keywords = new ArrayList<>();
		String temp = "";
		try {
			br = new BufferedReader(
					new InputStreamReader(new FileInputStream(args[0])));
			while((temp=br.readLine())!=null){
				if(keywords.indexOf(temp)==-1){
					keywords.add(temp);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found!");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error when close");
		}
		
		ac = new GoodsCrawlManager(MAX, "", new JDCrawler());
		for(String str:keywords){
			ac.setKeyword(str);
			ac.start();
		}
		
		ac = new GoodsCrawlManager(MAX, "", new DDCrawler());
		for(String str:keywords){
			ac.setKeyword(str);
			ac.start();
		}
	}
}
