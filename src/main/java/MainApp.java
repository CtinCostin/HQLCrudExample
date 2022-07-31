import dao.StudentDao;
import entity.Student;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        StudentDao studentDao = new StudentDao();
        Student student = new Student("Costi", "Georgescu", "costi@yahoo.com");
        //insert student
        studentDao.saveStudent(student);
        studentDao.insertStudent();

        //update student
        Student student1 = new Student("Constantin", "Georgescu", "costi@yahoo.com");
        studentDao.updateStudent(student1);

        //get students
        List<Student> studentList = studentDao.getStudents();
        studentList.forEach(s -> System.out.println(s.getFirstName()));

        //get single student
        Student student2 = studentDao.getStudent(1);
        System.out.println(student2.getFirstName());

        //delete student
       // studentDao.deleteStudent(1);
    }
}
