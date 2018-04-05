package pers.xiaoming.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeSuite;
import pers.xiaoming.mybatis.dao.IStudentDao;
import pers.xiaoming.mybatis.dao.SessionManager;
import pers.xiaoming.mybatis.dao.StudentDaoImpl;
import pers.xiaoming.mybatis.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InitDB {

    private static final int NUM_OF_DATA_GENERATE = 2;

    private static final String NAME_PREFIX = "John";

    private static final double SCORE_START = 80;

    private static List<Student> students;

    public static List<Student> getStudents() {
        return students;
    }

    public static int getNumOfDataGenerate() {
        return NUM_OF_DATA_GENERATE;
    }

    @BeforeSuite
    public static void InitDB() {
        try (SqlSession session = SessionManager.getSession()) {
            session.delete("alias.truncateTStudentTable");
            session.commit();
        }

        students = new ArrayList<>();
        IStudentDao dao = new StudentDaoImpl();
        Random random = new Random();

        for (int i = 0; i < NUM_OF_DATA_GENERATE; i++) {
            Student student = Student.builder()
                    .name(NAME_PREFIX + i)
                    .score(SCORE_START + random.nextInt((int)(100 - SCORE_START)))
                    .build();
            students.add(student);
            dao.create(student);
        }
    }
}
