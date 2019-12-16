package raky.train.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import raky.train.entity.User;

/**
 * spring mvc ==> view ===> controller
 * 
 * type2 : ͨ����װ������ (�β�)
 * 		view ===> controller  ==> (User user)
 * 		controller ===> view  ==> return new User();
  * ���Ƽ�
 * 
  * �ص㣺��ͼ������Ʋ㽻�����ݣ�ͨ�������β����������ݰ�
 * 
 * @author raky
 *
 */
@Controller
public class Type3Controller {
	
	private static final Logger logger = Logger.getLogger(Type3Controller.class);
	
	/**
	  * ��������ҳ���ȡ�������
	 * view ==> controller
	 * @param name
	 * @param pass
	 * @return
	 */
	@RequestMapping("/type3/get")
	@ResponseBody
	public User getData(@RequestBody User user) {
		logger.info("view ==> controller : user = " + user);
		return user;
	}
	
	/**
	 * ��������ҳ����������
	 * controller ==> view
	 * @return
	 */
	@RequestMapping("/type3/push")
	@ResponseBody
	public User pushData() {
		logger.info("controller ==> view : user (name = raky, pass = 1234)");
		return User.builder().name("raky").pass("1234").build();
	}

}
