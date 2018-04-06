package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.self_relation.EmployeeSuper;

public interface SelfOneToManyDao {
    EmployeeSuper selectEmployeeWithSubs(int id);
}
