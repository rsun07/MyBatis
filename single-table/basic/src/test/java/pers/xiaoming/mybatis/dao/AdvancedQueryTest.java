package pers.xiaoming.mybatis.dao;

import org.testng.Assert;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.InitDB;
import pers.xiaoming.mybatis.entity.Student;

import java.util.List;

public class AdvancedQueryTest {

    private IStudentConDao dao = new StudentConDaoImpl();

    @Test
    public void testGetByFuzzyName() {
        List<Student> students = dao.getByFuzzyName("John");

        Assert.assertEquals(students, InitDB.getStudents());
    }

    @Test
    public void testStudentAsParameter() {
        Student student = new Student("Mike", 79);

        List<Student> students = dao.getScoreHigherThanGivenStudent(student);

        Assert.assertEquals(students, InitDB.getStudents());
    }
}
