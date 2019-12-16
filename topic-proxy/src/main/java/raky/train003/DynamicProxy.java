package raky.train003;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ��̬����
 *   1.Ŀ��ӿ�   ProductDao
 *   2.����ʵ��   ProductDaoImpl
 *   3.�Զ�����   ʵ��InvocationHandler�ӿ�  ��дinvoke����
 *   4.����Proxy.newProxyInstance(Ŀ�����ļ�����, Ŀ�����Ľӿ�, InvocationHandlerʵ�������);
 * 
 * @author raky
 *
 */
public class DynamicProxy implements InvocationHandler {
	
	private Object target; //Ŀ�����
	
	//���������Ŀ������������
	public DynamicProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("��־��ʼ......[�Ǻ���ҵ��]");
		Object o = method.invoke(target, args); //���ú���ҵ��
		System.out.println("��־����......[�Ǻ���ҵ��]");
		return o;
	}

}
