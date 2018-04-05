package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.Student;

import java.util.List;

// Conditional query interface
public interface IStudentConDao {

    List<Student> getByFuzzyName(String fuzzyName);

    List<Student> getScoreHigherThanGivenStudent(Student student);

    List<Student> getNameLikeAndScoreHigherThanGivenStudent(String fuzzyName, Student student);
}
