package pers.xiaoming.mybatis;

import org.apache.ibatis.session.SqlSession;
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

    /*

        IStudentDao.selectByIdCopy - ==>  Preparing: select id,name,score from student where id=?
        IStudentDao.selectByIdCopy - ==> Parameters: 0(Integer)
        IStudentDao.selectByIdCopy - <==      Total: 0
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@6156496]
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@6156496]
        DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Returned connection 102065302 to pool.
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Opening JDBC Connection
        DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Checked out connection 102065302 from pool.
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@6156496]
        IStudentDao.selectByIdCopy - ==>  Preparing: select id,name,score from student where id=?
        IStudentDao.selectByIdCopy - ==> Parameters: 0(Integer)
        IStudentDao.selectByIdCopy - <==      Total: 0
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@6156496]
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@6156496]
        DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Returned connection 102065302 to pool.
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Opening JDBC Connection
        DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Checked out connection 102065302 from pool.
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@6156496]
        IStudentDao.selectByIdCopy - ==>  Preparing: select id,name,score from student where id=?
        IStudentDao.selectByIdCopy - ==> Parameters: 0(Integer)
        IStudentDao.selectByIdCopy - <==      Total: 0
     */

    @Test
    public void testCacheDisabled() {
        // not an assert test
        // see console out put

        Student student = new Student();
        student.setId(0);

        for (int i = 0; i < 3; i++) {
            runCopy(student);
        }
    }

    private void runCopy(Student student) {
        SqlSession session = SessionManager.getSession();
        IStudentDao dao = session.getMapper(IStudentDao.class);
        dao.selectByIdCopy(student);
        session.close();
    }


    /*

        IStudentDao.selectById - ==>  Preparing: select id,name,score from student where id=?
        IStudentDao.selectById - ==> Parameters: 0(Integer)
        IStudentDao.selectById - <==      Total: 0
        IStudentDao.insertStudent - ==>  Preparing: insert into student(name, score) values (?,?)
        IStudentDao.insertStudent - ==> Parameters: NewStu(String), 90.0(Double)
        IStudentDao.insertStudent - <==    Updates: 1
        IStudentDao.insertStudent!selectKey - ==>  Preparing: select @@identity
        IStudentDao.insertStudent!selectKey - ==> Parameters:
        IStudentDao.insertStudent!selectKey - <==      Total: 1
        IStudentDao - Cache Hit Ratio [pers.xiaoming.mybatis.dao.IStudentDao]: 0.0
        IStudentDao.selectById - ==>  Preparing: select id,name,score from student where id=?
        IStudentDao.selectById - ==> Parameters: 0(Integer)
        IStudentDao.selectById - <==      Total: 0
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Rolling back JDBC Connection [com.mysql.jdbc.JDBC4Connection@5ad851c9]
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Resetting autocommit to true on JDBC Connection [com.mysql.jdbc.JDBC4Connection@5ad851c9]
        DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@5ad851c9]
        DEBUG org.apache.ibatis.datasource.pooled.PooledDataSource - Returned connection 1524126153 to pool.

     */
    @Test
    public void testFlushCache() {
        // not an assert test
        // see console out put

        Student student = new Student();
        student.setId(0);

        SqlSession session = SessionManager.getSession();
        IStudentDao dao = session.getMapper(IStudentDao.class);
        dao.selectById(student);

        Student newStudent = new Student("NewStu", 90.0);
        dao.insertStudent(newStudent);

        dao.selectById(student);
        session.close();
    }
}
