package raky.train.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import raky.train.entity.Users;
import raky.train.util.DbUtil;

/**
 * 1.HQL查询 [Hibernate Query Lanague]
 * 2.QBC查询 [Hibernate Query Criteria] -Criteria
 * 3.QBC查询 [Hibernate Query Criteria] -DetachedCriteria 动态查询
 * 4.QBE查询 [Hibernate Query Example]
 * 5.SQL查询[Structured Query Language]-NativeSQL本地SQL查询
 * 6.命名查询-调用配置文件中的SQL语句
 * 
 * hibernate查询
 * 1.hql查询，基于对象（类）的查询语句  ==> 编写类查询语句(hql) [hibernate5完全支持]
 * 2.Criteria,以对象的形式封装查询语句 ==> java代码[hibernate5 ==> JPA方式]
 * 3.原生sql ==> 数据库支持的sql语句 ==> [hibernate5 ==> 支持，适合复杂查询]
 * 
 * 
 * @author raky
 *
 */
public class QueryDao {
	
	private static final Logger logger = LoggerFactory.getLogger(QueryDao.class);
	
	private Session session = null;
	
	private CriteriaBuilder criteriaBuilder;
	
	/**
	 * 1.HQL查询，使用命名参数，推荐使用[Hibernate Query lanague]
	 * 	from Users as users where users.name = :name
	 * 	select * from users a where a.name = ?
	 *  第一种：users.name =:name  参数绑定    相当于预处理中的？占位符
	 *  	hql 必须给参数赋值  query.setParameter("name", "jack1");
	 *  第二种：users.name = ?1  预处理占位符
	 * 		from Users as users where users.name = ?1 and users.age = ?2
	 * 		query.setParameter(1, "xxx");
			query.setParameter(2, 20);
			
		多表关联：
			from User as user, Role as role where user.id=role.user
			from User user inner join user.role
			[inner join, left join, right join] 没有on条件
			full join / cross join ==> 不需要查询条件		
			
			最佳使用场景：报表统计
				多表查询 ===》视图[接口开发]  ==》存储过程 [金融，银行类系统]
				hibernat多表hql语句===>多表关联  + 延迟加载
		
	 * 
	 * 推荐使用
	 */
	public void query1() {
		session = DbUtil.getSession();
		String hql = "from Users as users where users.name = ?1 and users.age = ?2";
//		hql = "from Users as users where users.name = :name and users.age = :age";
//		hql = "from Users users where users.age > :age";
		hql = "from Users users where users.name like '%?1%'";
		hql = "select count(*) from Users users";
		
		Query<?> query = session.createQuery(hql);
		query.setParameter(1, "x");
//		query.setParameter("age", 20);

		query.list().forEach(x -> logger.info("{}",x));
		session.close();
	}
	
	/**
	 * 2.QBC:Criteria对象化查询[Query By Criteria]
	 * 推荐使用 ===>jpa方式  按照对象的方式去组织查询语句
	 * 		与session关联
	 * 
	 * session.createCriteria(Users.class)  ==> select * from users
	 * Restrictions.like("name","jack") ==> name like '%jack%'
	 * Restrictions.eq("age",18) ==> age = 18
	 */
	public void query2() {
		session = DbUtil.getSession();
		//构造查询条件的对象
		criteriaBuilder = session.getCriteriaBuilder();
		//相当于创建sql语句 ==> select * from users
		CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery(Users.class);
		Root<Users> root = criteriaQuery.from(Users.class);
		//条件1
		Predicate name =  criteriaBuilder.equal(root.get("name"),"zzz");
		Predicate age =  criteriaBuilder.ge(root.get("age"),18);
		criteriaQuery.where(name,age);//eq是等于，gt是大于，lt是小于,or是或
		session.createQuery(criteriaQuery).getResultList().forEach(x -> logger.info("{}",x));		
		session.close();
	}
	

	/**
	 * 3.QBC 动态分离查询DetachedCriteria
	 * 	查询语句的对象是单独创建的
	 * sql语句对象与session对象解耦
	 * 
	 * 不常用，不推荐
	 */
	@SuppressWarnings("unchecked")
	public void query3() {			
		session = DbUtil.getSession();
		//创建一个分离的查询语句的对象  ==>select * from users
		DetachedCriteria dc = DetachedCriteria.forClass(Users.class);
//	   dc.add(Restrictions.eq("id", 1));
	   //构造查询条件
	   //dc.add(Restrictions.le("age", 20));
//	   dc.add(Restrictions.like("name", "z", MatchMode.END));
	   dc.add(Restrictions.ilike("name", "z", MatchMode.END));
	   
	   Session session2 = DbUtil.getSession();
	   //sql语句对象与session关联
	   Criteria c = dc.getExecutableCriteria(session2);
	   
	   c.list().forEach(x -> logger.info("{}",x));		
	   session2.close();
	}
	
	/**
	 * 4.QBE例子查询，实际使用很少 [Query By Example]
	 * 	 QBE例子查询 ===> JPA QBE代替
	 * session.createCriteria(Users.class)  ==> select * from users
	 * Restrictions.like("name","jack") ==> name like '%jack%'
	 * Restrictions.eq("age",18) ==> age = 18
	 * 
	 * 不推荐
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void query4() {
		session = DbUtil.getSession();
		List<Users> usersList = session.createCriteria(Users.class).add(
			    Example.create(new Users())).list();
		for(Users users : usersList){
			logger.info("{}",users);
		}		
		session.close();
	}
	
	/**
	 * 5.SQL ,NativeSQL 使用原生sql查询
	 * 结合具体数据库，适合复杂查询使用
	 *  预处理占位符?, jpa ==> ?1, hibernate ==> :name
	 */
	public void query5() {
	  session = DbUtil.getSession();
	  Query<?> query = session.createSQLQuery("select * from users where name=:name")
			  .addEntity(Users.class);
	  query.setParameter("name", "zzz");
	  query.list().forEach(x -> logger.info("{}",x));			
		session.close();
	}
	
	/**
	 * 6.使用命名查询，需要在模块的配置文件xml中添加查询语句
	 * 适合复杂查询使用
	 * 相当于mybatis中xml存储的sql语句
	 */
	@SuppressWarnings("unchecked")
	public void query6() {
		session = DbUtil.getSession();
		Query<Users> query = session.getNamedQuery("getUsersByName");
		query.setParameter("name", "xxx");
		query.list().forEach(x -> logger.info("{}",x));			
		session.close();
	}

	public static void main(String[] args) {		
		QueryDao queryDao = new QueryDao();
//		queryDao.query1();
//		queryDao.query2();
//		queryDao.query3();
//		queryDao.query4();
//		queryDao.query5();
		queryDao.query6();
	}
		
}
