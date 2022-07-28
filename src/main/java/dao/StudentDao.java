package dao;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class StudentDao {

    private SessionFactory sessionFactory;

    public void insertStudent() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "INSERT INTO Student (firstName, lastName, email)" +
                "SELECT firstName, lastName, email FROM Student";
        Query query = session.createQuery(hql);
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);

        transaction.commit();
        session.close();
    }

    public void updateStudent(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Student SET firstName = :firstName " + "WHERE id = :studentId";
        Query query = session.createQuery(hql);
        query.setParameter("firstName", student.getFirstName());
        query.setParameter("studentId", 1);
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);

        transaction.commit();
        session.close();
    }

    public void deleteStudent(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, id);
        if (student != null) {
            String hql = "DELETE FROM Student" + "WHERE id = :studentId";
            Query query = session.createQuery(hql);
            query.setParameter("studentId", id);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
        }
        transaction.commit();
        session.close();
    }


    public Student getStudent(Integer id) {
        Student student = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = " FROM Student S WHERE S.id = :studentId";
        Query query = session.createQuery(hql);
        query.setParameter("studentId", id);
        List results = query.getResultList();
        if (results != null && !results.isEmpty()) {
            student = (Student) results.get(0);
        }
        transaction.commit();
        session.close();

        return student;
    }

    public List<Student> getStudents() {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM Student", Student.class).list();
    }
}



