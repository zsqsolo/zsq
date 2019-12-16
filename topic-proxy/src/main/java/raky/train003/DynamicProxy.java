package raky.train003;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理：
 *   1.目标接口   ProductDao
 *   2.具体实现   ProductDaoImpl
 *   3.自定义类   实现InvocationHandler接口  重写invoke方法
 *   4.调用Proxy.newProxyInstance(目标对象的加载器, 目标对象的接口, InvocationHandler实现类对象);
 * 
 * @author raky
 *
 */
public class DynamicProxy implements InvocationHandler {
	
	private Object target; //目标对象
	
	//代理对象与目标对象产生关联
	public DynamicProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("日志开始......[非核心业务]");
		Object o = method.invoke(target, args); //调用核心业务
		System.out.println("日志结束......[非核心业务]");
		return o;
	}

}
