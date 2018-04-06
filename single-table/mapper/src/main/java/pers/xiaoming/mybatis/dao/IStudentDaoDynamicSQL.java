package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.Student;

import java.util.List;

public interface IStudentDaoDynamicSQL {

    List<Student> selectDynamicSQLIf(Student student);

    List<Student> selectDynamicSQLChoose(Student student);

    List<Student> selectDynamicSQLForeachArray(int[] inputIds);

    List<Student> selectDynamicSQLForeachList(List<Integer> idList);

}
