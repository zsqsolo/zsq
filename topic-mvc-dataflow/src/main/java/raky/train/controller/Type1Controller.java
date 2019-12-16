package raky.train.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import raky.train.entity.User;

/**
 * spring mvc ==> view ===> controller
 * 
 * type1 : 通过HttpServletRequest对象 (形参)
 * 		view ===> controller  ==> request.getParameter(key);
 * 		controller ===> view  ==> request.setAttribute(key,value);
  * 不推荐
 * 
  * 特点：视图层与控制层交互数据，通过方法形参来进行数据绑定
 * 
 * @author raky
 *
 */
@Controller
public class Type1Controller {
	
	@RequestMapping("/type1")
	@ResponseBody
	public User type1(HttpServletRequest request) {
		User user = new User();
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		user.setName(name);
		user.setPass(pass);
		return user;
	}

}
