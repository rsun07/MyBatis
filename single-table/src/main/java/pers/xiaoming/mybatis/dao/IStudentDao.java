package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.Student;

import java.util.List;
import java.util.Map;

public interface IStudentDao {
    int create(Student student);

    Student get(int id);

    List<Student> getAll();

    Map<String, Object> getAll(String fieldName);

    void update(Student student);

    void delete(int id);
}
