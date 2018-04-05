package pers.xiaoming.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.InitDB;
import pers.xiaoming.mybatis.entity.Student;

public class DynamicMapperTest {
    private Student student = new Student("John", 88.5);
    private static SqlSession session;
    private static IStudentDaoDynamicProxy dao;
    private static IStudentDao newDao;

    @BeforeClass
    public static void setup() {
        session = SessionManager.getSession();
        dao = session.getMapper(IStudentDaoDynamicProxy.class);
        newDao = new StudentDaoImpl();
    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }


    @Test
    public void testCreate() {
        Assert.assertEquals(student.getId(), 0);
        int id = dao.insertStudent(student);

        // Haven't commit yet!!!
        // When use session.getMapper() rather than implement dao in Java code
        // need to manually commit();

        // currently, in cache or snapshot, the student instance's id is already updated
        // but the id from db haven't updated.
        Assert.assertNotEquals(student.getId(), id);

        // the student.getId() is already updated but DB doesn't has the record
        // as the insert haven't been commit yet
        // new dao will open a session to query the db directly each time
        Student studentInDB = newDao.get(student.getId());
        Assert.assertNull(studentInDB);

        session.commit();

        // the insertStudent() returned int id cannot be used as auto generated key
        // the auto generated key is in student.getId()
        // auto assigned by MyBatis
        Assert.assertNotEquals(student.getId(), id);

        validateWithGet(student);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testUpdate() {
        // initial db score cannot bigger than 100
        // So use 108 for update test
        double scoreUpdated = 108.0;
        student.setScore(scoreUpdated);
        dao.updateStudent(student);

        Student studentInDB = newDao.get(student.getId());
        Assert.assertNotEquals(studentInDB.getScore(), scoreUpdated);

        session.commit();

        studentInDB = newDao.get(student.getId());
        Assert.assertEquals(studentInDB.getScore(), scoreUpdated);
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() {
        dao.deleteStudent(student.getId());
        session.commit();
        validateWithGet(null);
    }

    // test Get One
    private void validateWithGet(Student studentExpect) {
        Student studentReturn = newDao.get(student.getId());
        Assert.assertEquals(studentReturn, studentExpect);
    }
}
