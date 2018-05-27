package com.marketplace.dao.impl;

import com.marketplace.dao.UserDAO;
import com.marketplace.entity.User;
import com.marketplace.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    public User getByLoginPassword(String login, String password) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List users;
        User user = new User();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(User.class);
            criteria.setMaxResults(1);

            if (login != null && !"".equals(login)) {
                criteria.add(Restrictions.eq("login", login));
            }
            if (password != null && !"".equals(password)) {
                criteria.add(Restrictions.eq("password", password));
            }

            users = criteria.list();
            user = (User) users.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }

    public List<User> getUsers() throws SQLException {
        List<User> users = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query= session.createQuery("from User ");
            users = query.getResultList();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return users;
    }

    public void add(User user) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void update(User user) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void delete(User user) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}