package raky.train002;

public class Test {
	
	public static void main(String[] args) {
		//1.Ŀ�������Ҫ����ɻ�
		ProductDao productDao = new ProductDaoImpl();
//		productDao.insert(); //ֱ�Ӹɻ�
		
		//2.��̬���������Ŀ������������
		ProductDao proxy = new StaticProxyProductDao(productDao);
		//3.���ô������ķ���
		proxy.insert(); //�������ɻ�
		
	}

}
