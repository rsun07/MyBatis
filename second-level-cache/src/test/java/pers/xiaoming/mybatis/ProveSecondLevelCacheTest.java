package pers.xiaoming.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.dao.IStudentDao;
import pers.xiaoming.mybatis.dao.SessionManager;
import pers.xiaoming.mybatis.entity.Student;

public class ProveSecondLevelCacheTest {

    /*
        IStudentDao.selectById - ==>  Preparing: select id,name,score from student where id=?
        IStudentDao.selectById - ==> Parameters: 0(Integer)
        IStudentDao.selectById - <==      Total: 0
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@13acb0d1]
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@13acb0d1]
        DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Returned connection 330084561 to pool.
        IStudentDao - Cache Hit Ratio [pers.xiaoming.mybatis.dao.IStudentDao]: 0.5
        IStudentDao - Cache Hit Ratio [pers.xiaoming.mybatis.dao.IStudentDao]: 0.6666666666666666
        IStudentDao - Cache Hit Ratio [pers.xiaoming.mybatis.dao.IStudentDao]: 0.75
     */
    @Test
    public void testProve() {
        // not an assert test
        // see console out put
        // only query db once

        Student student = new Student();
        student.setId(0);

        for (int i = 0; i <=3; i++) {
            run(student);
        }
    }

    private void run(Student student) {
        SqlSession session = SessionManager.getSession();
        IStudentDao dao = session.getMapper(IStudentDao.class);
        dao.selectById(student);
        session.close();
    }
}
