package lk.ijse.bistroculinaryacademyorm.dao.custom.impl;

import lk.ijse.bistroculinaryacademyorm.config.SessionFactoryConfig;
import lk.ijse.bistroculinaryacademyorm.dao.custom.StudentDAO;
import lk.ijse.bistroculinaryacademyorm.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private Session session;
    @Override
    public List<Student> getAll() throws Exception {
        List<Student> all = new ArrayList<>();
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        all = session.createQuery("from Student").list();
        transaction.commit();
        session.close();
        return all;

//        try{
//            List<Student> students = session.createNativeQuery("SELECT * FROM student", Student.class)
//                    .getResultList();
//            return (List<Student>) students;
//        }catch (Exception e){
//            return null;
//        }finally {
//            session.close();
//        }
    }

    @Override
    public boolean add(Student entity) throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
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

        Student student = session.get(Student.class, id);
        if (student != null) {
            session.delete(student);
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
    public Student searchStudent(String contact) throws SQLException, IOException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        String hql = "FROM Student WHERE studentContact = :contact";
        Query<Student> query = session.createQuery(hql, Student.class);
        query.setParameter("contact", contact);
        return query.uniqueResult();
    }

    @Override
    public String generateNewID() {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String lastID = (String) session.createQuery("SELECT s.studentID FROM Student s ORDER BY s.studentID DESC")
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastID != null) {
                int id = Integer.parseInt(lastID.replace("S", "")) + 1;
                return "S" + String.format("%03d", id);
            } else {
                return "S001";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean IdExists(String studentId) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String hql = "SELECT COUNT(s.studentID) FROM Student s WHERE s.studentID = :id";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("id", studentId);
            Long count = query.uniqueResult();
            return count != null && count > 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student getStudentById(String studentId) throws IOException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        try {
            Student student = session.get(Student.class, studentId);
            tx.commit();
            return student;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Student findStudentById(String studentId) throws IOException {
        Transaction transaction = null;
        Student student = null;

        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Student> query = session.createQuery("FROM Student s WHERE s.studentID = :id", Student.class);
            query.setParameter("id", studentId);
            student = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }

        return student;
    }

    @Override
    public int getStudentCount() throws SQLException {
        int studentCount = 0;
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(s) FROM Student s", Long.class);
            studentCount = query.uniqueResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed to fetch student count from the database", e);
        }
        return studentCount;
    }
    public List<String> getAllStudentIds() throws SQLException, ClassNotFoundException {
        List<String> studentIds = new ArrayList<>();
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Query<String> query = session.createQuery("SELECT s.studentID FROM Student s", String.class);
            studentIds = query.list();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return studentIds;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
