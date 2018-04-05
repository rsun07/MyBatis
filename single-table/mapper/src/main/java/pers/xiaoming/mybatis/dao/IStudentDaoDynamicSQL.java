package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.Student;

import java.util.List;

public interface IStudentDaoDynamicSQL {

    List<Student> selectStudentIf(Student student);
}
