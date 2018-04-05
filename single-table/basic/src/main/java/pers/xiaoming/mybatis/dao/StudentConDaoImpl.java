package pers.xiaoming.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import pers.xiaoming.mybatis.entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentConDaoImpl implements IStudentConDao {
    public List<Student> getByFuzzyName(String fuzzyName) {
        try (SqlSession session = SessionManager.getSession()) {
            return session.selectList("advanced_single_table_query.selectByFuzzyName", fuzzyName);
        }
    }

    public List<Student> getScoreHigherThanGivenStudent(Student student) {
        try (SqlSession session = SessionManager.getSession()) {
            // MyBatis Executor will get the fields from student
            // and inject into SQL query
            return session.selectList("selectScoreHigherThanGivenStudent", student);
        }
    }

    public List<Student> getNameLikeAndScoreHigherThanGivenStudent(String fuzzyName, Student student) {
        Map<String, Object> map = new HashMap<>();

        map.put("nameLike", fuzzyName);
        map.put("stu", student);

        try (SqlSession session = SessionManager.getSession()) {
            return session.selectList("selectNameLikeAndScoreHigherThanGivenStudent", map);
        }
    }
}
