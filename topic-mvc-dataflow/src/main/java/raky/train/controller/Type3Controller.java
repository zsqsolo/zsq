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
 * type2 : 通过包装的数据 (形参)
 * 		view ===> controller  ==> (User user)
 * 		controller ===> view  ==> return new User();
  * 不推荐
 * 
  * 特点：视图层与控制层交互数据，通过方法形参来进行数据绑定
 * 
 * @author raky
 *
 */
@Controller
public class Type3Controller {
	
	private static final Logger logger = Logger.getLogger(Type3Controller.class);
	
	/**
	  * 控制器从页面获取打包数据
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
	 * 控制器向页面推送数据
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
