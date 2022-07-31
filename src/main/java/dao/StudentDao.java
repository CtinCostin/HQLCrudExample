package dao;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class StudentDao {


    public void saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    public void insertStudent() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "INSERT INTO Student (firstName, lastName, email)" +
                    "SELECT firstName, lastName, email FROM Student";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "UPDATE Student SET firstName = :firstName " + "WHERE id = :studentId";
            Query query = session.createQuery(hql);
            query.setParameter("firstName", student.getFirstName());
            query.setParameter("studentId", 1);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    public void deleteStudent(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Student student = session.get(Student.class, id);
            if (student != null) {
                String hql = "DELETE FROM Student WHERE id = :studentId";
                Query query = session.createQuery(hql);
                query.setParameter("studentId", id);
                int result = query.executeUpdate();
                System.out.println("Rows affected: " + result);
            }
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }


    public Student getStudent(Integer id) {
        Student student = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = " FROM Student S WHERE S.id = :studentId";
            Query query = session.createQuery(hql);
            query.setParameter("studentId", id);
            List results = query.getResultList();
            if (results != null && !results.isEmpty()) {
                student = (Student) results.get(0);
            }
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
        return student;
    }

    public List<Student> getStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        }
    }
}



