package raky.train003;

/**
 * 产品具体实现
 * @author raky
 *
 */
public class ProductDaoImpl implements ProductDao {

	@Override
	public void insert() {
		System.out.println("添加商品----[核心业务]");
	}

	@Override
	public void update() {
		System.out.println("修改商品----[核心业务]");
	}

	@Override
	public void delete() {
		System.out.println("删除商品----[核心业务]");
	}

}
