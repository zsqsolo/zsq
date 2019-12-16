package raky.train003;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理：
 *   1.目标接口   ProductDao
 *   2.具体实现   ProductDaoImpl
 *   3.自定义类   实现InvocationHandler接口
 *   
 *  把创建代理对象的方法，放在InvocationHandler实现类的内部
 * 
 * @author zr
 *
 */
public class DynamicInvocationHander implements InvocationHandler {
	
	private Object target; //目标对象 ==》productDao
	
	
	//方法返回结果  -==》生成代理对象【万能的代理对象】
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("日志开始...");
		Object o = method.invoke(target, args);//执行目标对象的目标方法
		System.out.println("日志结束...");
		return o;
	}

}
