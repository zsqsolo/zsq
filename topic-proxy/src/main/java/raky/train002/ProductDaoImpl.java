package raky.train002;

/**
 * ��Ʒ����ʵ��
 * @author raky
 *
 */
public class ProductDaoImpl implements ProductDao {

	@Override
	public void insert() {
		System.out.println("�����Ʒ-����ҵ��");
	}

	@Override
	public void update() {
		System.out.println("�޸���Ʒ");
	}

	@Override
	public void delete() {
		System.out.println("ɾ����Ʒ");
	}

}
