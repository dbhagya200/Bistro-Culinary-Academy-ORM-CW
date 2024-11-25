package lk.ijse.bistroculinaryacademyorm.dao.custom.impl;

import lk.ijse.bistroculinaryacademyorm.config.SessionFactoryConfig;
import lk.ijse.bistroculinaryacademyorm.dao.custom.CourseDAO;
import lk.ijse.bistroculinaryacademyorm.entity.Course;
import lk.ijse.bistroculinaryacademyorm.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    private Session session;


    @Override
    public List<Course> getAll() throws Exception {
        String hql = "FROM Course";
        Query<Course> query = session.createQuery(hql, Course.class);
        return query.list();
    }

    @Override
    public boolean add(Course entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean update(Course entity) throws Exception {
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
