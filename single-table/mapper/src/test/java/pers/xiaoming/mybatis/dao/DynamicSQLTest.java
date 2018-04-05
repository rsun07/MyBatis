package pers.xiaoming.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.InitDB;
import pers.xiaoming.mybatis.entity.Student;

import java.util.Arrays;
import java.util.List;

public class DynamicSQLTest {
    private Student student = new Student("John", 88.5);
    private static SqlSession session;
    private static IStudentDaoDynamicSQL dao;

    @BeforeClass
    public static void setup() {
        session = SessionManager.getSession();
        dao = session.getMapper(IStudentDaoDynamicSQL.class);
    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }

    @Test(dataProvider = "test_if_data_provider")
    public void testIf(Student query, List<Student> expected) {
        List<Student> studentReturn = dao.selectStudentIf(query);
        Assert.assertEquals(studentReturn, expected);
    }

    @DataProvider(name = "test_if_data_provider")
    public Object[][] testIfDataProvider() {
        List<Student> initialedStudent = InitDB.getStudents();
        Student John0 = initialedStudent.get(0);
        Student John1 = initialedStudent.get(1);

        Student higherScore;
        Student lowerScore;

        if (John0.getScore() > John1.getScore()) {
            higherScore = John0;
            lowerScore = John1;
        } else {
            higherScore = John1;
            lowerScore = John0;
        }

        return new Object[][] {
                // only name
                {new Student("John0"), Arrays.asList(John0)},
                // only score
                {new Student(lowerScore.getScore()), Arrays.asList(higherScore)},
                // both params
                {new Student("John", lowerScore.getScore()), Arrays.asList(higherScore)},
                // return multiple rows
                {new Student("John", lowerScore.getScore() - 1), Arrays.asList(John0, John1)},
                // none params
                {new Student(), Arrays.asList(John0, John1)}
        };
    }
}
