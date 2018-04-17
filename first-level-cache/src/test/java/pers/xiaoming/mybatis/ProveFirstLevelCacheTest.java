package pers.xiaoming.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.dao.IStudentDao;
import pers.xiaoming.mybatis.dao.SessionManager;
import pers.xiaoming.mybatis.entity.Student;

public class ProveFirstLevelCacheTest {
    private static SqlSession session;
    private static IStudentDao dao;

    @BeforeClass
    public static void setup() {
        session = SessionManager.getSession();
        dao = session.getMapper(IStudentDao.class);
    }

    /*
        IStudentDao.selectById - ==>  Preparing: select id,name,score from student where id=?
        IStudentDao.selectById - ==> Parameters: 1(Integer)
        IStudentDao.selectById - <==      Total: 0
     */
    @Test
    public void testProve() {
        // not an assert test
        // see console out put
        // only query db once
        Student student = new Student();
        student.setId(0);
        dao.selectById(student);
        dao.selectById(student);
    }
}
