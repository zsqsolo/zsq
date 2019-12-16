package raky.train003;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * jdk动态代理  ==》aop动态代理
 * 	 1.父接口  ==> 目标对象和代理对象需要实现的接口
 *   2.自定义一个类，实现InvocationHandler接口，重写invoke()方法
 *   
	 *   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("日志开始......[非核心业务]");
			Object o = method.invoke(target, args); //调用核心业务
			System.out.println("日志结束......[非核心业务]");
			return o;
		 }
		 proxy ： 代理对象
		 Method： 代理对象的方法
		 Object[]：代理对象方法的参数
		  method.invoke(target, args); ==> 调用目标对象的核心业务
		  invoke方法里面追加非核心业务，达到效果：
		  	在不改变现有目标对象的源代码的情况下，额外增加功能。
	3.根据第2步对象创建代理对象
		Proxy.newProxyInstance(目标对象的类加载器, 
				目标对象的父接口, InvocationHandler接口实现类对象)
 * 
 * @author 15256
 *
 */
public class Test {
	
	public static void test1() {
		//1.目标对象：需要具体干活
		ProductDao productDao = new ProductDaoImpl();
		
		//2.创建DynamicProxy对象 
		DynamicProxy invocation = new DynamicProxy(productDao);
		
		//3.创建代理对象		
		ProductDao proxy = (ProductDao)Proxy.newProxyInstance(productDao.getClass().getClassLoader(), 
				productDao.getClass().getInterfaces(), invocation);
		
		//4.调用代理对象的方法
		proxy.insert();
		proxy.update();
		proxy.delete();
	}
	
	
	public static void test2() {
		//1.目标对象
		ProductDao productDao = new ProductDaoImpl();
		//2.代理对象   ===》 注入目标对象
		ProductDao proxy = (ProductDao)new DynamicInvocationHander().bind(productDao);
		//3.调用代理对象的方法
		proxy.insert(); //==>  DynamicInvocationHander ==> invoke()
		// ==> Object o = method.invoke(target, args);//执行目标对象的目标方法
		// ==> productDao.insert()
	}
	
	public static void test3() {
		ProductDao productDao = new ProductDaoImpl();
		//接口的匿名实现类
		ProductDao proxy = (ProductDao)new InvocationHandler() {			
			Object target; //目标对象
			
			//在bind方法中创建代理对象
			public Object bind(Object target) {
				this.target = target;
				return Proxy.newProxyInstance(productDao.getClass().getClassLoader(),
						productDao.getClass().getInterfaces(),this);
			}

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("日志开始......[非核心业务]");
				Object o = method.invoke(target, args);
				System.out.println("日志结束......[非核心业务]");
				return o;
			}}.bind(productDao);
		proxy.insert();
		
	}
	
	public static void test4() {
		ProductDao productDao = new ProductDaoImpl();
		
		ProductDao proxy = (ProductDao)Proxy.newProxyInstance(productDao.getClass().getClassLoader(), 
			productDao.getClass().getInterfaces(), new InvocationHandler() {
				Object target; //目标对象
				
				public InvocationHandler bind(Object target) {
					this.target = target;
					return this;
				}
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					Object o = method.invoke(target, args);
					return o;
				}
			}.bind(productDao));
		proxy.insert();
		
	}
	
	public static void  test5() {
		//目标对象：需要具体干活
		UsersDao usersDao = new UsersDaoImpl();
		
		//创建DynamicProxy对象
		DynamicProxy invocation = new DynamicProxy(usersDao);
		
		//创建代理对象		
		UsersDao proxy = (UsersDao)Proxy.newProxyInstance(usersDao.getClass().getClassLoader(), 
				usersDao.getClass().getInterfaces(), invocation);

//		proxy.insert();
		proxy.update();
	}
	
	public static void test6() {
		//代理产品管理
		ProductDao productDao = new ProductDaoImpl();
		ProductDao proxy1 = (ProductDao)new DynamicInvocationHander().bind(productDao);
		proxy1.insert();
		
		//代理用户管理
		UsersDao usersDao = new UsersDaoImpl();
		UsersDao proxy2 = (UsersDao)new DynamicInvocationHander().bind(usersDao);
		proxy2.insert();
	}
	
	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
//		test4();
//		test5();
		test6();
		
//		Thread t1 = new Thread(new Runnable() {			
//			@Override
//			public void run() {
//				
//			}
//		});
	}

}
