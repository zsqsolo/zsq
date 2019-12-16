package raky.train003;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ��̬����
 *   1.Ŀ��ӿ�   ProductDao
 *   2.����ʵ��   ProductDaoImpl
 *   3.�Զ�����   ʵ��InvocationHandler�ӿ�
 *   
 *  �Ѵ����������ķ���������InvocationHandlerʵ������ڲ�
 * 
 * @author zr
 *
 */
public class DynamicInvocationHander implements InvocationHandler {
	
	private Object target; //Ŀ����� ==��productDao
	
	
	//�������ؽ��  -==�����ɴ���������ܵĴ������
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("��־��ʼ...");
		Object o = method.invoke(target, args);//ִ��Ŀ������Ŀ�귽��
		System.out.println("��־����...");
		return o;
	}

}
