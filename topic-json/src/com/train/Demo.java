package com.train;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author junki
 * @date 2019年9月1日
 */
public class Demo {
	
	public static void main(String[] args) {
		
		Girlfriend girlfriend = new Girlfriend("邹四强", '女', 20);
		//1.java对象转为json文本
		String text = JSON.toJSONString(girlfriend);
		System.out.println(text);
		//{"age":20,"name":"邹四强","sex":"女"}
		
		//2.json文本转为java对象
		girlfriend = JSON.parseObject("{\"age\":28,\"name\":\"邹四强\",\"sex\":\"女\"}", Girlfriend.class);
		System.out.println(girlfriend);
		
	}
	
}
