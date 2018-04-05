package pers.xiaoming.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import pers.xiaoming.mybatis.entity.Student;

import java.util.List;
import java.util.Map;

public class StudentDaoImpl implements IStudentDao {
    // ### Cause: java.lang.IllegalArgumentException: insertStudent is ambiguous in Mapped Statements collection
    // (try using the full name including the namespace, or rename one of the entries)
    public int create(Student student) {
        try (SqlSession session = SessionManager.getSession()) {
            session.insert("single_table.insertStudent", student);
            session.commit();
            return student.getId();
        }
    }

    public Student get(int id) {
        try (SqlSession session = SessionManager.getSession()) {
            return session.selectOne("single_table.selectStudent", id);
        }
    }

    public List<Student> getAll() {
        try (SqlSession session = SessionManager.getSession()) {
            List<Student> students = session.selectList("single_table.selectAllStudents");
            return students;
        }
    }

    public Map<String, Object> getAll(String fieldName) {
        try (SqlSession session = SessionManager.getSession()) {
            // the statement is the same as List<Student> getAll() method
            Map<String, Object> students = session.selectMap("single_table.selectAllStudents", fieldName);
            return students;
        }
    }

    public void update(Student student) {
        try (SqlSession session = SessionManager.getSession()) {
            session.update("single_table.updateStudent", student);
            session.commit();
        }
    }

    public void delete(int id) {
        try (SqlSession session = SessionManager.getSession()) {
            // session.delete("single_table.deleteStudent", id);

            // the library implementation create() and delete() all call update()
            session.update("single_table.deleteStudent", id);

            session.commit();
        }
    }
}
