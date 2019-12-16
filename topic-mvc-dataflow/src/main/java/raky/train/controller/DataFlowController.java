package raky.train.controller;

import org.springframework.stereotype.Controller;

/**
 * spring mvc 控制器接收页面数据
 *  1、HttpServletRequest --> request.getParameter()
 *  2、表单参数注入到方法参数，方法参数与表单的name属性值保持一致 [散装数据]
 *  3、自动注入bean [打包数据]
 *  	页面    <input name="name"/> 
 *  	    <input name="pass"/>  
 *		实体类 Users --> name, pass
 *		控制器方法：参数Users --> query
 *  4、@RequestParam  url参数   [需要传递参数，控制必选项]
          @PathVariable url路径
          @RequestBody 请求对象数据（json）
 *
 * spring mvc 控制器向页面传送数据
 * 1、HttpServletRequest setAttribute
 * 2、ModelAndView对象
 * 	  Users users = usersService.getUsers(id)
 * 	  Map<String,Object> data = new HashMap<String,Object>();
 * 	  data.put("users",users);
 * 	  return new ModelAndView("input",model);
 * 3、ModelMap对象
 * 4、@ModelAttribute注解      出入口，设定key
 * 
 * spring mvc 控制器重定向， 不受解析视图的限制
 * 1、使用RedirectView
 * 		 RedirectView view = new RedirectView("queryUsers");  
 * 2、使用redirect:前缀
 * 	   	 物理资源
 * 
 * @author raky
 *
 */
@Controller
public class DataFlowController {

}
