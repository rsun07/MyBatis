package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.n_to_m.Customer;

public interface NToMDao {
    Customer selectCustomerById(int id);
}
