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
    public void testStudentAsParameterToInjectSQL() {
        Student student = new Student("Mike", 79);

        List<Student> students = dao.getScoreHigherThanGivenStudent(student);

        Assert.assertEquals(students, InitDB.getStudents());
    }


    /*
    23:18:57.401 [main] DEBUG advanced_single_table_query.selectNameLikeAndScoreHigherThanGivenStudent - ==>  Preparing: select id,name,score from student where name like '%' ? '%' and score > ?
    23:18:57.401 [main] DEBUG advanced_single_table_query.selectNameLikeAndScoreHigherThanGivenStudent - ==> Parameters: John(String), 79.0(Double)
     */
    @Test
    public void testMapAsParameterToInjectSQL() {
        Student student = new Student("Mike", 79);

        List<Student> students = dao.getNameLikeAndScoreHigherThanGivenStudent("John", student);

        Assert.assertEquals(students, InitDB.getStudents());
    }
}
