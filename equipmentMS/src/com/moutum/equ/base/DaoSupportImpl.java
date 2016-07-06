package com.moutum.equ.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.moutum.equ.dto.PageBean;
import com.moutum.equ.util.QueryHelper;

/************************************************************************************
 * @Title        : DaoSupportImpl.java
 * @Description : 
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月6日 下午5:33:24
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
@Transactional
@SuppressWarnings("unchecked")
public class DaoSupportImpl<T> implements DaoSupport<T>
{
    @Resource private SessionFactory sessionFactory;
    
    private Class<T> clazz;
    
    public DaoSupportImpl()
    {
        this.clazz = null;
        Class<?> c = getClass();
        Type t = c.getGenericSuperclass();
        if(t instanceof ParameterizedType)
        {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.clazz = (Class<T>) p[0];
        }
    }
    
    /**********************************************************************************
     * 获取当前可用的Session
     * 
     * @return
     **********************************************************************************/
    protected Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public void save(T entity)
    {
        getSession().persist(entity);
    }

    @Override
    public void delete(Long id)
    {
        Object obj = getById(id);
        if(obj != null)
        {
            getSession().delete(obj);
        }
    }

    @Override
    public void update(T entity)
    {
        getSession().merge(entity);
    }

    @Override
    public T getById(Long id)
    {
        if (id == null)
        {
            return null;
        }
        else
        {
            return (T) getSession().get(clazz, id);
        }
    }

    @Override
    public List<T> getByIds(Long[] ids)
    {
        if (ids == null || ids.length == 0)
        {
            return Collections.EMPTY_LIST;
        }
        else
        {
            return getSession().createQuery("FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")
                               .setParameterList("ids", ids).list();
        }
    }

    @Override
    public List<T> findAll()
    {
        return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper)
    {
     // 参数列表
        List<Object> parameters = queryHelper.getParameters();

        // 查询本页的数据列表
        Query listQuery = getSession().createQuery(queryHelper.getListQueryHql()); // 创建查询对象
        if (parameters != null) 
        { // 设置参数
            for (int i = 0; i < parameters.size(); i++) 
            {
                listQuery.setParameter(i, parameters.get(i));
            }
        }
        listQuery.setFirstResult((pageNum - 1) * pageSize);
        listQuery.setMaxResults(pageSize);
        List list = listQuery.list(); // 执行查询

        // 查询总记录数量
        Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
        if (parameters != null) 
        { // 设置参数
            for (int i = 0; i < parameters.size(); i++) 
            {
                countQuery.setParameter(i, parameters.get(i));
            }
        }
        Long count = (Long) countQuery.uniqueResult(); // 执行查询

        return new PageBean(pageNum, pageSize, count.intValue(), list);
    }
}