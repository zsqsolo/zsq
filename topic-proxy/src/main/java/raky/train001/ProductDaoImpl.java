package raky.train001;

/**
 * ��Ʒ����ʵ��
 * @author zr
 *
 */
public class ProductDaoImpl implements ProductDao {

	@Override
	public void insert() {
		System.out.println("�����Ʒ");
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
