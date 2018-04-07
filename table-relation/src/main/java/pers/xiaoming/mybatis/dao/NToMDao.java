package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.n_to_m.Customer;
import pers.xiaoming.mybatis.entity.n_to_m.Store;

public interface NToMDao {
    Customer selectCustomerById(int id);

    Store selectStoreById(int id);
}
