package raky.train003;

/**
 * 产品具体实现
 * 
 * @author raky
 *
 */
public class UsersDaoImpl implements UsersDao {

	@Override
	public void insert() {
		System.out.println("添加用户");
	}

	@Override
	public void update() {
		System.out.println("修改用户");
	}

	@Override
	public void delete() {
		System.out.println("删除用户");
	}

}
