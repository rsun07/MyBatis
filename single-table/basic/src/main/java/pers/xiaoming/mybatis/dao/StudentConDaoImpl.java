package pers.xiaoming.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import pers.xiaoming.mybatis.entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentConDaoImpl implements IStudentConDao {
    public List<Student> getByFuzzyName(String fuzzyName) {
        try (SqlSession session = SessionManager.getSession()) {
            return session.selectList("single_table.selectByFuzzyName", fuzzyName);
        }
    }

    public List<Student> getNameLikeAndScoreHigherThanGivenStudent(String fuzzyName, Student student) {
        Map<String, Object> map = new HashMap<>();

        map.put("name", fuzzyName);
        map.put("stu", student);

        try (SqlSession session = SessionManager.getSession()) {
            return session.selectList("selectNameLikeAndScoreHigherThanGivenStudent", map);
        }
    }
}
