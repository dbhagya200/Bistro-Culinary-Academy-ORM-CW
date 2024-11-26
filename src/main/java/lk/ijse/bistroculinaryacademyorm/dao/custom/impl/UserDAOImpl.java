package lk.ijse.bistroculinaryacademyorm.dao.custom.impl;

import lk.ijse.bistroculinaryacademyorm.config.SessionFactoryConfig;
import lk.ijse.bistroculinaryacademyorm.dao.custom.UserDAO;
import lk.ijse.bistroculinaryacademyorm.entity.Student;
import lk.ijse.bistroculinaryacademyorm.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Session session;

    @Override
    public List<User> getAll() throws Exception {
        String hql = "FROM User";
        Query<User> query = session.createQuery(hql, User.class);
        return query.list();
    }

    @Override
    public boolean add(User entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
