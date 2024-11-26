package lk.ijse.bistroculinaryacademyorm.dao.custom.impl;

import lk.ijse.bistroculinaryacademyorm.config.SessionFactoryConfig;
import lk.ijse.bistroculinaryacademyorm.dao.custom.CourseDAO;
import lk.ijse.bistroculinaryacademyorm.entity.Course;
import lk.ijse.bistroculinaryacademyorm.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    private Session session;


    @Override
    public List<Course> getAll() throws Exception {
        ArrayList<Course> courses = new ArrayList<>();
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            courses = (ArrayList<Course>) session.createQuery("FROM Course").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public boolean add(Course entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        Course course = session.get(Course.class, id);
        if (course != null) {
            session.delete(course);
            tx.commit();
            session.close();
            return true;
        } else {
            tx.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Course search(String id) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            return session.get(Course.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String generateNewID() {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String lastID = (String) session.createQuery("SELECT c.course_id FROM Course c ORDER BY c.course_id DESC")
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastID != null) {
                int id = Integer.parseInt(lastID.replace("C", "")) + 1;
                return "C" + String.format("%03d", id);
            } else {
                return "C001";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isCourseExists(String id) throws IOException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT COUNT(c.course_id) FROM Course c WHERE c.course_id = :id";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("id", id);
            Long count = query.uniqueResult();
            return count != null && count > 0;
        }

    }

    @Override
    public int getCourseCount() throws SQLException, ClassNotFoundException {
        int courseCount = 0;
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(c) FROM Course c", Long.class);
            Long result = query.uniqueResult();
            if (result != null) {
                courseCount = result.intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to fetch course count from the database", e);
        }
        return courseCount;
    }

    @Override
    public Course getCourseById(String courseId) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        try {
            // Fetch the course object based on the ID
            Course course = session.get(Course.class, courseId);
            tx.commit();  // Commit the transaction
            return course;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Course findCourseById(String courseId) throws Exception {
        Transaction transaction = null;
        Course course = null;

        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Course> query = session.createQuery("FROM Course c WHERE c.course_id = :id", Course.class);
            query.setParameter("id", courseId);
            course = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }

        return course;
    }


}
