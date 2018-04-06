package pers.xiaoming.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.dao.OneToManyDao;
import pers.xiaoming.mybatis.dao.SessionManager;
import pers.xiaoming.mybatis.entity.one_to_many.City;
import pers.xiaoming.mybatis.entity.one_to_many.Person;

import java.util.HashSet;
import java.util.Set;

public class SelfOneToManyTest {
    private static SqlSession session;
    private static OneToManyDao dao;
    private static City expectedCity;

    @BeforeClass
    public static void setup() {
        session = SessionManager.getSession();
        dao = session.getMapper(OneToManyDao.class);

        Set<Person> residents = new HashSet<>();
        residents.add(new Person(2,"John2"));
        residents.add(new Person(3,"John3"));

        expectedCity = new City(2, "C2", residents);
    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }

    /*
     Pre inserted data:

     INSERT INTO city (name) VALUES ("C1");
     INSERT INTO city (name) VALUES ("C2");

     INSERT INTO person (name, city_id) VALUES ("John1", 1);
     INSERT INTO person (name, city_id) VALUES ("John2", 2);
     INSERT INTO person (name, city_id) VALUES ("John3", 2);
     */

    @Test
    public void testSelectById() {
        City actualCity = dao.selectCityById(expectedCity.getId());

        Assert.assertEquals(actualCity, expectedCity);

        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testTwoQueriesSelect() {
        City actualCity = dao.selectCityByIdTwoQueries(expectedCity.getId());

        Assert.assertEquals(actualCity, expectedCity);

        if (session != null) {
            session.close();
        }
    }
}
