package pers.xiaoming.mybatis.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SessionManager {
    private static SqlSessionFactory sessionFactory;

    private SessionManager() {}

    public static SqlSession getSession() {
        if (sessionFactory == null) {
            initFactory();
        }
        return sessionFactory.openSession();
    }

    private static synchronized void initFactory() {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
}
