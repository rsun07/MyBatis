package pers.xiaoming.mybatis.dao;

import org.testng.Assert;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.InitDB;
import pers.xiaoming.mybatis.entity.Student;

import java.util.List;

public class GetByFuzzyNameTest {
    @Test
    public void test() {
        IStudentDao dao = new StudentDaoImpl();

        List<Student> students = dao.getByFuzzyName("John");

        Assert.assertEquals(students, InitDB.getStudents());
    }
}
