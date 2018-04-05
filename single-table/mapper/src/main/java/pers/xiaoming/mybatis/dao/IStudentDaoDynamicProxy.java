package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.Student;

public interface IStudentDaoDynamicProxy {
    // the method name should be exactly the same as
    // the CURD id name in mapper.xml

    int insertStudent(Student student);

    void updateStudent(Student student);

    Student selectStudent(int id);

    void deleteStudent(int id);
}
