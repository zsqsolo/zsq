package raky.train.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import raky.train.entity.User;

/**
 * spring mvc ==> view ===> controller
 * 
 * type2 : 通过散装的数据 (形参)
 * 		view ===> controller  ==> (String name, String pass)
 * 		controller ===> view  ==> return "cc";
  * 不推荐
 * 
  * 特点：视图层与控制层交互数据，通过方法形参来进行数据绑定
 * 
 * @author raky
 *
 */
@Controller
public class Type2Controller {
	
	private static final Logger logger = Logger.getLogger(Type2Controller.class);
	
	/**
	  * 控制器从页面获取散装数据
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
	 * 控制器向页面推送数据
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
