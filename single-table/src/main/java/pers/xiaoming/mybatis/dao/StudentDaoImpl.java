package pers.xiaoming.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import pers.xiaoming.mybatis.entity.Student;

import java.util.List;

public class StudentDaoImpl implements IStudentDao {

    public int create(Student student) {
        SqlSession session = SessionManager.getSession();
        session.insert("insertStudent", student);
        session.commit();
        return student.getId();
    }

    public Student get(int id) {
        return null;
    }

    public List<Student> getAll() {
        return null;
    }

    public void update(Student student) {
        SqlSession session = SessionManager.getSession();
        session.update("updateStudent", student);
        session.commit();
    }

    public void delete(int id) {

    }
}
