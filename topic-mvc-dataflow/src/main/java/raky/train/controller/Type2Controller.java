package raky.train.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import raky.train.entity.User;

/**
 * spring mvc ==> view ===> controller
 * 
 * type2 : ͨ��ɢװ������ (�β�)
 * 		view ===> controller  ==> (String name, String pass)
 * 		controller ===> view  ==> return "cc";
  * ���Ƽ�
 * 
  * �ص㣺��ͼ������Ʋ㽻�����ݣ�ͨ�������β����������ݰ�
 * 
 * @author raky
 *
 */
@Controller
public class Type2Controller {
	
	private static final Logger logger = Logger.getLogger(Type2Controller.class);
	
	/**
	  * ��������ҳ���ȡɢװ����
	 * view ==> controller
	 * @param name
	 * @param pass
	 * @return
	 */
	@RequestMapping("/type2/get")
	@ResponseBody
	public User getData(String name, String pass) {
		logger.info("view ==> controller : name = " + name + ", pass = " + pass);
		return User.builder().name(name).pass(pass).build();
	}
	
	/**
	 * ��������ҳ����������
	 * controller ==> view
	 * @return
	 */
	@RequestMapping("/type2/push")
	@ResponseBody
	public User pushData() {
		logger.info("controller ==> view : name = raky, pass = 1234");
		return User.builder().name("raky").pass("1234").build();
	}
	
	@RequestMapping("/type2/push2")
	@ResponseBody
	public String pushData2() {
		logger.info("controller ==> view : name = raky, pass = 1234");
		return "cc";
	}
}
