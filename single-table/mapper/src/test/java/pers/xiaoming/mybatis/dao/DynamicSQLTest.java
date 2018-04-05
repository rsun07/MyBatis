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
    private static SqlSession session;
    private static IStudentDaoDynamicSQL dao;

    private static Student John0;
    private static Student John1;

    private static Student higherScoreStu;
    private static Student lowerScoreStu;


    @BeforeClass
    public static void setup() {
        session = SessionManager.getSession();
        dao = session.getMapper(IStudentDaoDynamicSQL.class);

        List<Student> initialedStudent = InitDB.getStudents();
        John0 = initialedStudent.get(0);
        John1 = initialedStudent.get(1);

        if (John0.getScore() > John1.getScore()) {
            higherScoreStu = John0;
            lowerScoreStu = John1;
        } else {
            higherScoreStu = John1;
            lowerScoreStu = John0;
        }

    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }

    @Test(dataProvider = "test_if_data_provider")
    public void testDynamicSQLIf(Student query, List<Student> expected) {
        List<Student> studentReturn = dao.selectDynamicSQLIf(query);
        Assert.assertEquals(studentReturn, expected);
    }

    @DataProvider(name = "test_if_data_provider")
    public Object[][] testIfDataProvider() {
        return new Object[][] {
                // only name
                {new Student("John0"), Arrays.asList(John0)},
                // only score
                {new Student(lowerScoreStu.getScore()), Arrays.asList(higherScoreStu)},
                // both params
                {new Student("John", lowerScoreStu.getScore()), Arrays.asList(higherScoreStu)},
                // return multiple rows
                {new Student("John", lowerScoreStu.getScore() - 1), Arrays.asList(John0, John1)},
                // none params
                {new Student(), Arrays.asList(John0, John1)}
        };
    }

    @Test(dataProvider = "test_choose_data_provider")
    public void testDynamicSQLChoose(Student query, List<Student> expected) {
        List<Student> studentReturn = dao.selectDynamicSQLChoose(query);
        Assert.assertEquals(studentReturn, expected);
    }

    @DataProvider(name = "test_choose_data_provider")
    public Object[][] testChooseDataProvider() {
        return new Object[][] {
                // only name will apply, like switch
                // if both name and score apply, should be no result
                {new Student("John", higherScoreStu.getScore()), Arrays.asList(John0, John1)},

                // only score
                // like switch, no name, then go to score condition
                {new Student(lowerScoreStu.getScore()), Arrays.asList(higherScoreStu)},

                // none params, go to otherwise
                {new Student(), Arrays.asList()}
        };
    }
}
