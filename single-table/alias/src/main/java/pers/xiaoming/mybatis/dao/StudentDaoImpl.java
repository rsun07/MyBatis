package pers.xiaoming.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import pers.xiaoming.mybatis.entity.Student;


public class StudentDaoImpl implements IStudentDao {

    public int create(Student student) {
        try (SqlSession session = SessionManager.getSession()) {
            session.insert("insertStudent", student);
            session.commit();
            return student.getId();
        }
    }

    public Student get(int id) {
        try (SqlSession session = SessionManager.getSession()) {
            return session.selectOne("selectStudent", id);
        }
    }


    public void update(Student student) {
        try (SqlSession session = SessionManager.getSession()) {
            session.update("updateStudent", student);
            session.commit();
        }
    }

    public void delete(int id) {
        try (SqlSession session = SessionManager.getSession()) {
            session.update("deleteStudent", id);
            session.commit();
        }
    }
}
