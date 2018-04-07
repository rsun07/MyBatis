package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.self_relation.EmployeeSub;
import pers.xiaoming.mybatis.entity.self_relation.EmployeeSuper;

import java.util.List;

public interface SelfManyToOneDao {
    EmployeeSub selectEmployeeWithSuper(int id);
}
