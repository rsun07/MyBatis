package pers.xiaoming.mybatis.dao;

import pers.xiaoming.mybatis.entity.one_to_many.City;

public interface OneToManyDao {
    City selectCityById(int id);

    City selectCityByIdTwoQueries(int id);
}
