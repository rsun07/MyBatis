package pers.xiaoming.mybatis.dao;


import org.testng.Assert;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.InitDB;
import pers.xiaoming.mybatis.entity.Student;

import java.util.List;
import java.util.Map;

public class GetAllTest {
    private IStudentDao dao = new StudentDaoImpl();

    @Test
    public void testGetAllList() {
        List<Student> students = dao.getAll();
        Assert.assertEquals(students, InitDB.getStudents());
    }

    @Test
    public void testGetAllMap() {
        Map<String, Object> map = dao.getAll("name");

        for (Student student : InitDB.getStudents()) {
            Assert.assertEquals(
                    map.get(student.getName()),
                    student);
        }
    }
}
