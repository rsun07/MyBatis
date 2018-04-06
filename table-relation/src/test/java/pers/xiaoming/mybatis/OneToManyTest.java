package pers.xiaoming.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.dao.OneToManyDao;
import pers.xiaoming.mybatis.dao.SessionManager;
import pers.xiaoming.mybatis.entity.one_to_many.City;
import pers.xiaoming.mybatis.entity.one_to_many.Person;

import java.util.HashSet;
import java.util.Set;

public class OneToManyTest {

    /*
     Pre inserted data:

     INSERT INTO city (name) VALUES ("C1");
     INSERT INTO city (name) VALUES ("C2");

     INSERT INTO person (name, city_id) VALUES ("John1", 1);
     INSERT INTO person (name, city_id) VALUES ("John2", 2);
     INSERT INTO person (name, city_id) VALUES ("John3", 2);
     */
    @Test
    public void test() {
        SqlSession session = SessionManager.getSession();
        OneToManyDao dao = session.getMapper(OneToManyDao.class);

        Set<Person> residents = new HashSet<>();
        residents.add(new Person(2,"John2"));
        residents.add(new Person(3,"John3"));

        City expected = new City(2, "C2", residents);
        City actual = dao.selectCityById(expected.getId());

        Assert.assertEquals(actual, expected);

        if (session != null) {
            session.close();
        }
    }

}
