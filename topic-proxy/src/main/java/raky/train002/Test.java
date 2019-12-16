package raky.train002;

public class Test {
	
	public static void main(String[] args) {
		//1.目标对象：需要具体干活
		ProductDao productDao = new ProductDaoImpl();
//		productDao.insert(); //直接干活
		
		//2.静态代理对象：与目标对象产生关联
		ProductDao proxy = new StaticProxyProductDao(productDao);
		//3.调用代理对象的方法
		proxy.insert(); //代理对象干活
		
	}

}
