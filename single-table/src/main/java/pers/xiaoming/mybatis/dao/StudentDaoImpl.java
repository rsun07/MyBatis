package pers.xiaoming.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import pers.xiaoming.mybatis.entity.Student;

public class StudentDaoImpl implements IStudentDao {

    public void insert(Student student) {
        SqlSession session = SessionManager.getSession();
        session.insert("insertStudent", student);
        session.commit();
    }
}
