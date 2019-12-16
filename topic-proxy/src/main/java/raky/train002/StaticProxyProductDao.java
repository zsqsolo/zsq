package raky.train002;

/**
 * ��̬����һ��������ֻ�ܴ���һ������
 * ���� ������Ķ������Դ�������ɻ�
 * 
 * @author raky
 *
 */
public class StaticProxyProductDao implements ProductDao{
	
	private ProductDao productDao; //�����Ŀ�����ӿ�
	
	//�Ѿ���ɻ��Ŀ�����ע����� ���������  ��---����---�� Ŀ�����
	public StaticProxyProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	//�������--�������Ʒ   ---����������Ŀ�����ȥ�����Ʒ
	//insert() �������ɵĻ�
	@Override
	public void insert() {
		//����Ŀ�����������Ʒ
		System.out.println("������Ʒ...[�Ǻ���ҵ��]");
		productDao.insert(); //Դ����ɻ�
		System.out.println("������...[�Ǻ���ҵ��]");
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
