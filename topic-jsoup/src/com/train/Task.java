package com.train;

import java.util.List;

import org.jsoup.nodes.Element;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

/**
 * 任务：
 * 		爬取起点小说网所有免费玄幻小说。
 * 		网址：https://www.qidian.com/free/all?chanId=21
 * 		要求：
 * 			1.每本小说创建一个文件夹
 * 			2.每章创建一个txt文件，存放纯文本内容（去除html代码）
 * 
 * @author junki
 * @date 2019年8月24日
 */
public class Task {
	
	public static void main(String[] args) {
		
		JXDocument doc = JXDocument.createByUrl("https://www.qidian.com/free/all?chanId=21");
		
		List<JXNode> nodes = doc.selN("//*[@id=\"free-channel-wrap\"]/div/div/div[2]/div[2]/div/ul/li/div[2]/h4/a");
		for (JXNode node : nodes) {
			System.out.println(node);
			Element element = node.asElement();
			JXDocument doc1 = JXDocument.createByUrl("https:" + element.attr("href"));
			List<JXNode> nodes1 = doc1.selN("//*[@id=\"j-catalogWrap\"]/div[2]/div/ul/li/a");
			for (JXNode node1 : nodes1) {
				System.out.println(node1);
			}
		}
		
	}
	
}
