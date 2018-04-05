package pers.xiaoming.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import pers.xiaoming.mybatis.entity.Student;


public class StudentDaoImpl implements IStudentDao {

    // ### Cause: java.lang.IllegalArgumentException: insertStudent is ambiguous in Mapped Statements collection
    // (try using the full name including the namespace, or rename one of the entries)
    public int create(Student student) {
        try (SqlSession session = SessionManager.getSession()) {
            session.insert("alias.insertStudent", student);
            session.commit();
            return student.getId();
        }
    }

    public Student get(int id) {
        try (SqlSession session = SessionManager.getSession()) {
            return session.selectOne("alias.selectStudent", id);
        }
    }


    public void update(Student student) {
        try (SqlSession session = SessionManager.getSession()) {
            // Don't need 'alias' because the name is different than
            // other statement id names in this project
            // so no ambiguous will cause
            session.update("updateStudentSpecialNameNoAmbiguous", student);
            session.commit();
        }
    }

    public void delete(int id) {
        try (SqlSession session = SessionManager.getSession()) {
            // Don't need 'alias' because the name is different than
            // other statement id names in this project
            // so no ambiguous will cause
            session.update("deleteStudentSpecialNameNoAmbiguous", id);
            session.commit();
        }
    }

    @Override
    public void truncateTable() {
        try (SqlSession session = SessionManager.getSession()) {
            // all the CURD method backend is calling update()
            session.update("alias.truncateTStudentTable");
            session.commit();
        }
    }
}
