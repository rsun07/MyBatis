package pers.xiaoming.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.dao.NToMDao;
import pers.xiaoming.mybatis.dao.OneToManyDao;
import pers.xiaoming.mybatis.dao.SessionManager;
import pers.xiaoming.mybatis.entity.n_to_m.Customer;
import pers.xiaoming.mybatis.entity.n_to_m.Store;
import pers.xiaoming.mybatis.entity.one_to_many.City;
import pers.xiaoming.mybatis.entity.one_to_many.Person;

import java.util.HashSet;
import java.util.Set;

public class NToMTest {
    private static SqlSession session;
    private static NToMDao dao;

    private static Customer c1;
    private static Customer c2;

    /*
         Pre inserted data:

         INSERT INTO customer (name) VALUES ("c1");
         INSERT INTO customer (name) VALUES ("c2");

         INSERT INTO store (name) VALUES ("s1");
         INSERT INTO store (name) VALUES ("s1");
         INSERT INTO store (name) VALUES ("s1");

         INSERT INTO customer_to_store (customer_id, store_id) VALUES (1, 2);
         INSERT INTO customer_to_store (customer_id, store_id) VALUES (1, 3);
         INSERT INTO customer_to_store (customer_id, store_id) VALUES (2, 1);
         INSERT INTO customer_to_store (customer_id, store_id) VALUES (2, 3);

     */


    @BeforeClass
    public static void setup() {
        session = SessionManager.getSession();
        dao = session.getMapper(NToMDao.class);

        c1 = new Customer(1, "c1");
        c2 = new Customer(2, "c2");

        Store s1 = new Store(1, "s1");
        Store s2 = new Store(2, "s2");
        Store s3 = new Store(3, "s3");

        c1.getStores().add(s1);
        c1.getStores().add(s2);

        c2.getStores().add(s1);
        c2.getStores().add(s3);
    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void testSelectById() {
        Customer customerReturn = dao.selectCustomerById(1);

        Assert.assertEquals(customerReturn, c1);
    }
}
