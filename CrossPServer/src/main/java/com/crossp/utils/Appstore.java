package com.crossp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import us.codecraft.xsoup.Xsoup;

import com.crossp.jpa.domain.StoreApp;

public class Appstore {
	public final static String appstorePrefix = "https://itunes.apple.com/en/app/id";
	
	public static StoreApp getFromWeb(String appstoreId) {
		StoreApp appItem = null;
		
		try {		
			String url = appstorePrefix + appstoreId;
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
			request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			request.addHeader("Accept-Encoding", "gzip,deflate,sdch");
			request.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
			request.addHeader("Connection", "keep-alive");
			request.addHeader("Host", "itunes.apple.com");
			request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36");
			HttpResponse response = client.execute(request);
			GZIPInputStream gzin = new GZIPInputStream(response.getEntity().getContent());
			InputStreamReader isr = new InputStreamReader(gzin);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			}
			isr.close();
			gzin.close();
			System.out.println(sb.toString());
		
			Document doc = Jsoup.parse(sb.toString());
			// name
			Element ele = Xsoup.compile("//meta[@property='og:title']").evaluate(doc).getElements().get(0);
			String name = ele.attr("content");

			// icon 175
			ele = Xsoup.compile("//div[@id='left-stack']//img").evaluate(doc).getElements().get(0);
			String icon175Link = ele.attr("src-swap");
			String icon350Link = ele.attr("src-swap-high-dpi");
			
			// type
			ele = Xsoup.compile("//li[@class='genre']/a").evaluate(doc).getElements().get(0);
			String type = ele.html();
			
			//description
			ele = Xsoup.compile("//div[@class='product-review']/p").evaluate(doc).getElements().get(0);
			String description = ele.html();
			
			//pic
			Elements elements = Xsoup.compile("//div[@class='lockup']/img").evaluate(doc).getElements();
			StringBuilder screenPictures = new StringBuilder();
			for (Element e : elements) {
				screenPictures.append(e.attr("src"));
				screenPictures.append("\r\n");
			}
			
			appItem = new StoreApp();
			appItem.setAppstoreId(appstoreId);
			appItem.setName(name);
			appItem.setType(type);
			appItem.setIcon175Link(icon175Link);
			appItem.setIcon350Link(icon350Link);
			appItem.setDescription(description);
			appItem.setScreenPictures(screenPictures.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			appItem = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			appItem = null;
		}
		
		return appItem;
	}
	
	public static void main(String[] args) throws Exception {
		getFromWeb("850417475");
	}
}
