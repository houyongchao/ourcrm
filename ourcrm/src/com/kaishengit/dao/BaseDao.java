package com.kaishengit.dao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;


@SuppressWarnings("unchecked")
public class BaseDao<T,PK extends Serializable> {

	
	private SessionFactory sessionfactory;
	private Class<?> clazz;
	
	
	public BaseDao() {
		
		Class<?> c = this.getClass();
		ParameterizedType pt = (ParameterizedType) c.getGenericSuperclass();
		Type[] types = pt.getActualTypeArguments();
		clazz = (Class<?>) types[0];
	}
	
	
	
	/**
	 * Spring
	 * @param sessionfactory
	 */
	@Autowired
	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}
	
	/**
	 *
	 * @return
	 */
	public Session getSession() {
		return sessionfactory.getCurrentSession();
	}
	
	/**
	 *
	 * @param t
	 */
	public void save(T t) {
		getSession().saveOrUpdate(t);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public T get(PK id) {
		return (T) getSession().get(clazz, id);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void delete(PK id){
		T t  = get(id);
		if(t != null){
			getSession().delete(t);
		}
	}
	
	
	/**
	 *
	 * @return
	 */
	public List<T> findAll() {
		Criteria c = getSession().createCriteria(clazz);
		return c.list();
	}
	
	/**
	 *
	 * @param start
	 * @return
	 */
	public List<T> findByPage(Integer start,Integer rows) {
		Criteria c = getSession().createCriteria(clazz);
		c.setFirstResult(start);
		c.setMaxResults(rows);
		return c.list();
	}
	/**
	 *
	 * @param start 
	 * @param rows
	 * @return
	 */
	public List<T> findByPageandWhere(Integer start,Integer rows,String propertyname,String value) {
		Criteria c = getSession().createCriteria(clazz);
		c.setFirstResult(start);
		c.setMaxResults(rows);
		c.add(Restrictions.eq(propertyname, value));
		c.addOrder(Order.desc("createtime"));
		return c.list();
	}
	
	
	
	/**
	 *
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public T findByUnique(String propertyName,String value) {
		Criteria c = getSession().createCriteria(clazz);
		c.add(Restrictions.eq(propertyName, value));
		return (T) c.uniqueResult();
	}
	
	/**
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findBy(String propertyName,String value) {
		Criteria c = getSession().createCriteria(clazz);
		c.add(Restrictions.eq(propertyName, value));
		return c.list();
	}
	/**
	 *
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findByContent(String propertyName,String value) {
		Criteria c = getSession().createCriteria(clazz);
		c.add(Restrictions.like(propertyName, value, MatchMode.ANYWHERE));
		c.addOrder(Order.desc("createtime"));
		return c.list();
	}
	
	/**
	 * 
	 * @param hql
	 * @param args
	 * @return
	 */
	public List<T> findByHql(String hql,Integer start,Integer rows ,Object...args) {
		return createQueryByPage(hql, start, rows, args).list();
	}
	
	/**
	 * 
	 * @param hql
	 * @param args
	 * @return
	 */
	public List<T> findByHql(String hql,Object...args) {
		return createQuery(hql, args).list();
	}
	
	/**
	 * 
	 * @param hql
	 * @param map
	 * @return
	 */
	public List<T> find(String hql,Map<String,Object> map) {
		return createQuery(hql, map).list();
	}
	
	/**
	 * 
	 * @param hql
	 * @param args
	 * @return
	 */
	public T findUniqueByHql(String hql,Object...args) {
		return (T) createQuery(hql, args).uniqueResult();
	}
	
	/**
	 * 
	 * @param hql
	 * @param map
	 * @return
	 */
	public T findUnique(String hql,Map<String,Object> map) {
		return (T) createQuery(hql, map).uniqueResult();
	}
	
	/**
	 *
	 * @param criterions
	 * @return
	 */
	public List<T> findByCriterion(Criterion... criterions) {
		return createCriteria(criterions).list();
	}
	
	/**
	 *
	 * @param criterions
	 * @return
	 */
	public T findUniqueByCriterion(Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}
	
	/**
	 * 
	 * @param wheres
	 * @return
	 */
	public List<T> findByWheres(List<Where> wheres){
		return builderCriteriaByWhereList(wheres).list();
	}
	
	/**
	 * 
	 * @param wheres
	 * @return
	 */
	public T findUniqueByWheres(List<Where> wheres) {
		return (T) builderCriteriaByWhereList(wheres).uniqueResult();
	}
	
	/**
	 * 
	 * @param wheres
	 * @return
	 */
	private Criteria builderCriteriaByWhereList(List<Where> wheres) {
		Criteria c = getSession().createCriteria(clazz);
		for(Where where : wheres) {
			//username_OR_loginname_OR_firstname
			if(where.getProperty().contains("_OR_")) {
				Criterion criterion = builderORCriterionByWhere(where);
				c.add(criterion);
			} else {
				Criterion criterion = builderCriterionByWhere(where);
				if(criterion != null) {
					c.add(criterion);
				}
			}
		}
		return c;
	}

	/**
	 * 
	 * @param where
	 * @return
	 */
	private Criterion builderORCriterionByWhere(Where where) {
		String[] propertyNames = where.getProperty().split("_OR_");
		
		
		Disjunction disjunction = Restrictions.disjunction();
		for(String property : propertyNames) {
			Criterion criterion = builderCriterionByWhere(where.getMatchType(), property, where.getValue());
			disjunction.add(criterion);
		}
		return disjunction;
	}



	/**
	 * 
	 * @param where
	 * @return
	 */
	private Criterion builderCriterionByWhere(Where where) {
		if(where != null) {
			String matchType = where.getMatchType();
			String property = where.getProperty();
			String value = where.getValue();
			return builderCriterionByWhere(matchType, property, value);
		}
		return null;
	}


	/**
	 * 
	 * @param matchType 
	 * @param property
	 * @param value
	 * @return
	 */
	public Criterion builderCriterionByWhere(String matchType, String property, String value) {
		if(MatchType.EQ.equalsIgnoreCase(matchType)) {
			return Restrictions.eq(property, value);
		} else if(MatchType.GE.equalsIgnoreCase(matchType)) {
			return Restrictions.ge(property, value);
		} else if(MatchType.GT.equalsIgnoreCase(matchType)) {
			return Restrictions.gt(property, value);
		} else if(MatchType.LE.equalsIgnoreCase(matchType)) {
			return Restrictions.le(property, value);
		} else if(MatchType.LT.equalsIgnoreCase(matchType)) {
			return Restrictions.lt(property, value);
		} else if(MatchType.LIKE.equalsIgnoreCase(matchType)) {
			return Restrictions.like(property, value, MatchMode.ANYWHERE);
		}
		return null;
	}



	/**
	 *
	 * @param criterions
	 * @return
	 */
	public Criteria createCriteria(Criterion... criterions) {
		Criteria c = getSession().createCriteria(clazz);
		for(Criterion cri : criterions) {
			c.add(cri);
		}
		return c;
	}
	
	/**
	 * 
	 * @param hql
	 * @param args
	 * @return
	 */
	public Query createQuery(String hql,Object...args) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		return query;
	}
	/**
	 *
	 * @param hql
	 * @param args
	 * @return
	 */
	public Query createQueryByPage(String hql,Integer start,Integer rows,Object...args) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		query.setFirstResult(start);
		query.setMaxResults(rows);
		return query;
	}
	
	/**
	 *
	 * @param hql
	 * @param map
	 * @return
	 */
	public Query createQuery(String hql,Map<String,Object> map) {
		Query query = getSession().createQuery(hql);
		query.setProperties(map);
		return query;
	}
	
	
	
}
