package example.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by root on 17-3-27.
 */
public class BaseDao<T, PK extends Serializable> {

    @Inject
    private SessionFactory sessionFactory;

    private Class<?> aClass;

    public BaseDao() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        aClass = (Class<?>) pt.getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Serializable save(T t) {
        return getSession().save(t);
    }

    public void saves(List<T> tList) {
        for (T t : tList) save(t);
    }

    public T getOne(PK id) {
        return (T) getSession().get(aClass, id);
    }

    public T get(Serializable id) {
        return (T) getSession().get(aClass, id);
    }

    public List<T> findAll() {
        Criteria criteria = getSession().createCriteria(aClass);
        return criteria.list();
    }

}
