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
 * 1.HQL��ѯ [Hibernate Query Lanague]
 * 2.QBC��ѯ [Hibernate Query Criteria] -Criteria
 * 3.QBC��ѯ [Hibernate Query Criteria] -DetachedCriteria ��̬��ѯ
 * 4.QBE��ѯ [Hibernate Query Example]
 * 5.SQL��ѯ[Structured Query Language]-NativeSQL����SQL��ѯ
 * 6.������ѯ-���������ļ��е�SQL���
 * 
 * hibernate��ѯ
 * 1.hql��ѯ�����ڶ����ࣩ�Ĳ�ѯ���  ==> ��д���ѯ���(hql) [hibernate5��ȫ֧��]
 * 2.Criteria,�Զ������ʽ��װ��ѯ��� ==> java����[hibernate5 ==> JPA��ʽ]
 * 3.ԭ��sql ==> ���ݿ�֧�ֵ�sql��� ==> [hibernate5 ==> ֧�֣��ʺϸ��Ӳ�ѯ]
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
	 * 1.HQL��ѯ��ʹ�������������Ƽ�ʹ��[Hibernate Query lanague]
	 * 	from Users as users where users.name = :name
	 * 	select * from users a where a.name = ?
	 *  ��һ�֣�users.name =:name  ������    �൱��Ԥ�����еģ�ռλ��
	 *  	hql �����������ֵ  query.setParameter("name", "jack1");
	 *  �ڶ��֣�users.name = ?1  Ԥ����ռλ��
	 * 		from Users as users where users.name = ?1 and users.age = ?2
	 * 		query.setParameter(1, "xxx");
			query.setParameter(2, 20);
			
		��������
			from User as user, Role as role where user.id=role.user
			from User user inner join user.role
			[inner join, left join, right join] û��on����
			full join / cross join ==> ����Ҫ��ѯ����		
			
			���ʹ�ó���������ͳ��
				����ѯ ===����ͼ[�ӿڿ���]  ==���洢���� [���ڣ�������ϵͳ]
				hibernat���hql���===>������  + �ӳټ���
		
	 * 
	 * �Ƽ�ʹ��
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
	 * 2.QBC:Criteria���󻯲�ѯ[Query By Criteria]
	 * �Ƽ�ʹ�� ===>jpa��ʽ  ���ն���ķ�ʽȥ��֯��ѯ���
	 * 		��session����
	 * 
	 * session.createCriteria(Users.class)  ==> select * from users
	 * Restrictions.like("name","jack") ==> name like '%jack%'
	 * Restrictions.eq("age",18) ==> age = 18
	 */
	public void query2() {
		session = DbUtil.getSession();
		//�����ѯ�����Ķ���
		criteriaBuilder = session.getCriteriaBuilder();
		//�൱�ڴ���sql��� ==> select * from users
		CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery(Users.class);
		Root<Users> root = criteriaQuery.from(Users.class);
		//����1
		Predicate name =  criteriaBuilder.equal(root.get("name"),"zzz");
		Predicate age =  criteriaBuilder.ge(root.get("age"),18);
		criteriaQuery.where(name,age);//eq�ǵ��ڣ�gt�Ǵ��ڣ�lt��С��,or�ǻ�
		session.createQuery(criteriaQuery).getResultList().forEach(x -> logger.info("{}",x));		
		session.close();
	}
	

	/**
	 * 3.QBC ��̬�����ѯDetachedCriteria
	 * 	��ѯ���Ķ����ǵ���������
	 * sql��������session�������
	 * 
	 * �����ã����Ƽ�
	 */
	@SuppressWarnings("unchecked")
	public void query3() {			
		session = DbUtil.getSession();
		//����һ������Ĳ�ѯ���Ķ���  ==>select * from users
		DetachedCriteria dc = DetachedCriteria.forClass(Users.class);
//	   dc.add(Restrictions.eq("id", 1));
	   //�����ѯ����
	   //dc.add(Restrictions.le("age", 20));
//	   dc.add(Restrictions.like("name", "z", MatchMode.END));
	   dc.add(Restrictions.ilike("name", "z", MatchMode.END));
	   
	   Session session2 = DbUtil.getSession();
	   //sql��������session����
	   Criteria c = dc.getExecutableCriteria(session2);
	   
	   c.list().forEach(x -> logger.info("{}",x));		
	   session2.close();
	}
	
	/**
	 * 4.QBE���Ӳ�ѯ��ʵ��ʹ�ú��� [Query By Example]
	 * 	 QBE���Ӳ�ѯ ===> JPA QBE����
	 * session.createCriteria(Users.class)  ==> select * from users
	 * Restrictions.like("name","jack") ==> name like '%jack%'
	 * Restrictions.eq("age",18) ==> age = 18
	 * 
	 * ���Ƽ�
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
	 * 5.SQL ,NativeSQL ʹ��ԭ��sql��ѯ
	 * ��Ͼ������ݿ⣬�ʺϸ��Ӳ�ѯʹ��
	 *  Ԥ����ռλ��?, jpa ==> ?1, hibernate ==> :name
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
	 * 6.ʹ��������ѯ����Ҫ��ģ��������ļ�xml����Ӳ�ѯ���
	 * �ʺϸ��Ӳ�ѯʹ��
	 * �൱��mybatis��xml�洢��sql���
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
