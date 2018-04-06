package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.self_relation.EmployeeSuper;

import java.util.List;

public interface SelfOneToManyDao {
    EmployeeSuper selectEmployeeWithSubs(int id);

    List<EmployeeSuper> selectSubsBySuperId(int id);
}
