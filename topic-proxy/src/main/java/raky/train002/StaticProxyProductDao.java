package raky.train002;

/**
 * 静态代理：一个代理类只能代理一件事情
 * 代理： 代理类的对象代表源对象来干活
 * 
 * @author raky
 *
 */
public class StaticProxyProductDao implements ProductDao{
	
	private ProductDao productDao; //代理的目标对象接口
	
	//把具体干活的目标对象注入进来 （代理对象  《---关联---》 目标对象）
	public StaticProxyProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	//代理对象--》添加商品   ---》代理具体的目标对象去添加商品
	//insert() 代理对象干的活
	@Override
	public void insert() {
		//调用目标对象的添加商品
		System.out.println("整理商品...[非核心业务]");
		productDao.insert(); //源对象干活
		System.out.println("整理库存...[非核心业务]");
	}
	
	@Override
	public void update() {
		productDao.update();
	}
	
	@Override
	public void delete() {
		productDao.delete();
	}
}
