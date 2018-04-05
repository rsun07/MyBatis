package pers.xiaoming.mybatis.dao;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.entity.Student;

public class CURDTest {
    private Student student = new Student("John", 88.5);
    private static IStudentDao dao;

    @BeforeClass
    public static void setup() {
        dao = new StudentDaoImpl();
    }

    @Test
    public void testCreate() {
        Assert.assertEquals(student.getId(), 0);
        int id = dao.create(student);
        // student instance already been set id by MyBatis
        Assert.assertEquals(id, student.getId());
        validateWithGet(student);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testUpdate() {
        student.setScore(98.5);
        dao.update(student);
        validateWithGet(student);
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() {
        dao.delete(student.getId());
        validateWithGet(null);
    }

    // test Get One
    private void validateWithGet(Student studentExpect) {
        Student studentReturn = dao.get(student.getId());
        Assert.assertEquals(studentReturn, studentExpect);
    }
}
