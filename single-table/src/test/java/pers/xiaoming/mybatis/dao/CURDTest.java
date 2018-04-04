package pers.xiaoming.mybatis.dao;

import org.junit.Assert;
import org.junit.Test;
import pers.xiaoming.mybatis.entity.Student;

public class CURDTest {
    private Student student = new Student("John", 88.5);
    private IStudentDao dao = new StudentDaoImpl();

    @Test
    public void testCreate() {
        Assert.assertEquals(student.getId(), 0);
        int id = dao.create(student);
        // student instance already been set id by MyBatis
        Assert.assertEquals(id, student.getId());
    }
}
