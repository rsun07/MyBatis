package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.Student;

public interface IStudentDao {
    Student selectById(Student student);
    Student selectByIdCopy(Student student);

    int insertStudent(Student student);
    void truncateStudentTable();
}
