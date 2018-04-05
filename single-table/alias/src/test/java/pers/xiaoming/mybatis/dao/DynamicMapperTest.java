package pers.xiaoming.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.entity.Student;

public class DynamicMapperTest {
    private Student student = new Student("John", 88.5);
    private static IStudentDaoDynamicProxy dao;

    @BeforeClass
    public static void setup() {
        SqlSession session = SessionManager.getSession();
        dao = session.getMapper(IStudentDaoDynamicProxy.class);
        dao.truncateTStudentTable();
    }

    @Test
    public void testCreate() {
        Assert.assertEquals(student.getId(), 0);
        int id = dao.insertStudent(student);
        // student instance already been set id by MyBatis
        Assert.assertEquals(id, student.getId());
        validateWithGet(student);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testUpdate() {
        student.setScore(98.5);
        dao.updateStudent(student);
        validateWithGet(student);
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() {
        dao.deleteStudent(student.getId());
        validateWithGet(null);
    }

    // test Get One
    private void validateWithGet(Student studentExpect) {
        Student studentReturn = dao.selectStudent(student.getId());
        Assert.assertEquals(studentReturn, studentExpect);
    }
}
