package raky.train.controller;

import org.springframework.stereotype.Controller;

/**
 * spring mvc ����������ҳ������
 *  1��HttpServletRequest --> request.getParameter()
 *  2��������ע�뵽�����������������������name����ֵ����һ�� [ɢװ����]
 *  3���Զ�ע��bean [�������]
 *  	ҳ��    <input name="name"/> 
 *  	    <input name="pass"/>  
 *		ʵ���� Users --> name, pass
 *		����������������Users --> query
 *  4��@RequestParam  url����   [��Ҫ���ݲ��������Ʊ�ѡ��]
          @PathVariable url·��
          @RequestBody ����������ݣ�json��
 *
 * spring mvc ��������ҳ�洫������
 * 1��HttpServletRequest setAttribute
 * 2��ModelAndView����
 * 	  Users users = usersService.getUsers(id)
 * 	  Map<String,Object> data = new HashMap<String,Object>();
 * 	  data.put("users",users);
 * 	  return new ModelAndView("input",model);
 * 3��ModelMap����
 * 4��@ModelAttributeע��      ����ڣ��趨key
 * 
 * spring mvc �������ض��� ���ܽ�����ͼ������
 * 1��ʹ��RedirectView
 * 		 RedirectView view = new RedirectView("queryUsers");  
 * 2��ʹ��redirect:ǰ׺
 * 	   	 ������Դ
 * 
 * @author raky
 *
 */
@Controller
public class DataFlowController {

}
