package com.train;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Element;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

/**
 * 爬虫类
 * @author junki
 * @date 2019年8月24日
 */
public class Crawler {
	
	public static void main(String[] args) {
		
		getAllPage();
		
	}

	//爬取所有页图片
	private static void getAllPage() {
		JXDocument doc = null;
		for (int i = 1; i <= 192; i++) {
			if (i == 1) {
				doc = JXDocument.createByUrl("http://pic.netbian.com/4kmeinv/index.html");
			} else {
				doc = JXDocument.createByUrl("http://pic.netbian.com/4kmeinv/index_" + i + ".html");
			}
			
			List<JXNode> imgs = doc.selN("//*[@id=\"main\"]/div[3]/ul/li/a/img");
			for (JXNode img : imgs) {
				Element element = img.asElement();
				String src = element.attr("src");
				String alt = element.attr("alt");
				
				try {
					FileUtils.copyURLToFile(new URL("http://pic.netbian.com" + src), new File("img/" + alt + src.substring(src.lastIndexOf("."))));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				System.out.println(alt + "爬取成功！");
			}
			System.out.println("第" + i + "页爬取完毕！");
		}
		
	}

	//爬取一页图片
	private static void getOnePage() {
		
		JXDocument doc = JXDocument.createByUrl("http://pic.netbian.com/4kmeinv/index_6.html");
		
		List<JXNode> imgs = doc.selN("//*[@id=\"main\"]/div[3]/ul/li/a/img");
		for (JXNode img : imgs) {
			Element element = img.asElement();
			String src = element.attr("src");
			String alt = element.attr("alt");
			
			try {
				FileUtils.copyURLToFile(new URL("http://pic.netbian.com" + src), new File("img/" + alt + src.substring(src.lastIndexOf("."))));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(alt + "爬取成功！");
		}
		System.out.println("一页爬取完毕！");
	}

	//爬取一张图片
	private static void getOneImg() {

		//1.创建目标网站的jsoup xpath document对象
		JXDocument doc = JXDocument.createByUrl("http://pic.netbian.com/");
		System.out.println("成功创建dom");
		
		//2.获取目标img标签
		//select node one
		JXNode img = doc.selNOne("//*[@id=\"main\"]/div[3]/ul/li[6]/a/span/img");
		System.out.println(img);
		
		//3.获取属性节点src和alt
		//src用于下载图片文件，alt作为图片名
		Element element = img.asElement();
		String src = element.attr("src");
		String alt = element.attr("alt");
		
		System.out.println(src);
		System.out.println(alt);
		
		//4.下载图片
		try {
			FileUtils.copyURLToFile(new URL("http://pic.netbian.com" + src), new File("img/" + alt + src.substring(src.lastIndexOf("."))));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(alt + "爬取成功！");
		
	}
	
}
