package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.self_relation.EmployeeSuper;

import java.util.List;

public interface SelfOneToManyDao {
    List<EmployeeSuper> selectSubBySuperId(int id);
}
