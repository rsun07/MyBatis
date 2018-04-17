package pers.xiaoming.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.dao.IStudentDao;
import pers.xiaoming.mybatis.dao.SessionManager;
import pers.xiaoming.mybatis.entity.Student;

public class ProveCacheUsageTest {
    private static SqlSession session;
    private static IStudentDao dao;

    @BeforeClass
    public static void setup() {
        session = SessionManager.getSession();
        dao = session.getMapper(IStudentDao.class);
    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }

    /*
        Query twice even for same Java Object result

        IStudentDao.selectById - ==>  Preparing: select id,name,score from student where id=?
        IStudentDao.selectById - ==> Parameters: 0(Integer)
        IStudentDao.selectById - <==      Total: 0

        IStudentDao.selectByIdCopy - ==>  Preparing: select id,name,score from student where id=?
        IStudentDao.selectByIdCopy - ==> Parameters: 0(Integer)
        IStudentDao.selectByIdCopy - <==      Total: 0
     */
    @Test
    public void testNotByJavaObject() {
        // not an assert test
        // see console out put
        Student student = new Student();
        student.setId(0);
        dao.selectById(student);
        dao.selectByIdCopy(student);
    }

    /*
        Only Query once

        IStudentDao.selectById - ==>  Preparing: select id,name,score from student where id=?
        IStudentDao.selectById - ==> Parameters: 0(Integer)
        IStudentDao.selectById - <==      Total: 0
     */
    @Test
    public void testBySqlIdAndParameterId() {
        // not an assert test
        // see console out put
        Student student = new Student();
        student.setId(0);
        dao.selectById(student);

        student.setName("Wrong_name_but_not_used_in_query");
        dao.selectById(student);
    }

    /*
        IStudentDao.insertStudent - ==>  Preparing: insert into student(name, score) values (?,?)
        IStudentDao.insertStudent - ==> Parameters: NewStu(String), 90.0(Double)
        IStudentDao.insertStudent - <==    Updates: 1
        IStudentDao.insertStudent!selectKey - ==>  Preparing: select @@identity
        IStudentDao.insertStudent!selectKey - ==> Parameters:
        IStudentDao.insertStudent!selectKey - <==      Total: 1
        IStudentDao.selectById - ==>  Preparing: select id,name,score from student where id=?
        IStudentDao.selectById - ==> Parameters: 0(Integer)
        IStudentDao.selectById - <==      Total: 0
     */

    @Test
    public void testCacheBusterByCUD() {
        // not an assert test
        // see console out put
        Student student = new Student();
        student.setId(0);
        dao.selectById(student);

        Student newStudent = new Student("NewStu", 90.0);
        dao.insertStudent(newStudent);
        dao.selectById(student);
    }
}
