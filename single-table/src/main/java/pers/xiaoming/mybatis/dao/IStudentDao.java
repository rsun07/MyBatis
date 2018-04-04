package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.Student;

import java.util.List;

public interface IStudentDao {
    int create(Student student);

    Student get(int id);

    List<Student> getAll();

    void update(Student student);

    void delete(int id);
}
