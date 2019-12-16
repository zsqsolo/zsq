package raky.train001;
/**
 * 目标对象，亲历亲为；自己去做事情。
 * @author 15256
 *
 */
public class Test {
	
	public static void main(String[] args) {
		ProductDao productDao = new ProductDaoImpl();
		productDao.insert();
		productDao.update();
		productDao.delete();
	}

}
