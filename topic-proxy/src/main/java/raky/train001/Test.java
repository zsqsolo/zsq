package raky.train001;
/**
 * Ŀ�����������Ϊ���Լ�ȥ�����顣
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
