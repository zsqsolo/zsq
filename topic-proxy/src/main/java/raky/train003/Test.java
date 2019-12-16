package raky.train003;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * jdk��̬����  ==��aop��̬����
 * 	 1.���ӿ�  ==> Ŀ�����ʹ��������Ҫʵ�ֵĽӿ�
 *   2.�Զ���һ���࣬ʵ��InvocationHandler�ӿڣ���дinvoke()����
 *   
	 *   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("��־��ʼ......[�Ǻ���ҵ��]");
			Object o = method.invoke(target, args); //���ú���ҵ��
			System.out.println("��־����......[�Ǻ���ҵ��]");
			return o;
		 }
		 proxy �� �������
		 Method�� �������ķ���
		 Object[]��������󷽷��Ĳ���
		  method.invoke(target, args); ==> ����Ŀ�����ĺ���ҵ��
		  invoke��������׷�ӷǺ���ҵ�񣬴ﵽЧ����
		  	�ڲ��ı�����Ŀ������Դ���������£��������ӹ��ܡ�
	3.���ݵ�2�����󴴽��������
		Proxy.newProxyInstance(Ŀ�������������, 
				Ŀ�����ĸ��ӿ�, InvocationHandler�ӿ�ʵ�������)
 * 
 * @author 15256
 *
 */
public class Test {
	
	public static void test1() {
		//1.Ŀ�������Ҫ����ɻ�
		ProductDao productDao = new ProductDaoImpl();
		
		//2.����DynamicProxy���� 
		DynamicProxy invocation = new DynamicProxy(productDao);
		
		//3.�����������		
		ProductDao proxy = (ProductDao)Proxy.newProxyInstance(productDao.getClass().getClassLoader(), 
				productDao.getClass().getInterfaces(), invocation);
		
		//4.���ô������ķ���
		proxy.insert();
		proxy.update();
		proxy.delete();
	}
	
	
	public static void test2() {
		//1.Ŀ�����
		ProductDao productDao = new ProductDaoImpl();
		//2.�������   ===�� ע��Ŀ�����
		ProductDao proxy = (ProductDao)new DynamicInvocationHander().bind(productDao);
		//3.���ô������ķ���
		proxy.insert(); //==>  DynamicInvocationHander ==> invoke()
		// ==> Object o = method.invoke(target, args);//ִ��Ŀ������Ŀ�귽��
		// ==> productDao.insert()
	}
	
	public static void test3() {
		ProductDao productDao = new ProductDaoImpl();
		//�ӿڵ�����ʵ����
		ProductDao proxy = (ProductDao)new InvocationHandler() {			
			Object target; //Ŀ�����
			
			//��bind�����д����������
			public Object bind(Object target) {
				this.target = target;
				return Proxy.newProxyInstance(productDao.getClass().getClassLoader(),
						productDao.getClass().getInterfaces(),this);
			}

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("��־��ʼ......[�Ǻ���ҵ��]");
				Object o = method.invoke(target, args);
				System.out.println("��־����......[�Ǻ���ҵ��]");
				return o;
			}}.bind(productDao);
		proxy.insert();
		
	}
	
	public static void test4() {
		ProductDao productDao = new ProductDaoImpl();
		
		ProductDao proxy = (ProductDao)Proxy.newProxyInstance(productDao.getClass().getClassLoader(), 
			productDao.getClass().getInterfaces(), new InvocationHandler() {
				Object target; //Ŀ�����
				
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
		//Ŀ�������Ҫ����ɻ�
		UsersDao usersDao = new UsersDaoImpl();
		
		//����DynamicProxy����
		DynamicProxy invocation = new DynamicProxy(usersDao);
		
		//�����������		
		UsersDao proxy = (UsersDao)Proxy.newProxyInstance(usersDao.getClass().getClassLoader(), 
				usersDao.getClass().getInterfaces(), invocation);

//		proxy.insert();
		proxy.update();
	}
	
	public static void test6() {
		//�����Ʒ����
		ProductDao productDao = new ProductDaoImpl();
		ProductDao proxy1 = (ProductDao)new DynamicInvocationHander().bind(productDao);
		proxy1.insert();
		
		//�����û�����
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
