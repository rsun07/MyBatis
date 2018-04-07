package pers.xiaoming.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.dao.SelfManyToOneDao;
import pers.xiaoming.mybatis.dao.SelfOneToManyDao;
import pers.xiaoming.mybatis.dao.SessionManager;
import pers.xiaoming.mybatis.entity.self_relation.EmployeeSub;
import pers.xiaoming.mybatis.entity.self_relation.EmployeeSuper;

import java.util.List;

public class SelfManyToOneTest {
    private static SqlSession session;
    private static SelfManyToOneDao dao;
    private static EmployeeSub engineer;
    private static EmployeeSub developer;
    private static EmployeeSub hr;

    @BeforeClass
    public static void setup() {
        session = SessionManager.getSession();
        dao = session.getMapper(SelfManyToOneDao.class);

        EmployeeSub ceo = new EmployeeSub("CEO_JOHN", "CEO");
        ceo.setId(1);

        EmployeeSub hrHead = new EmployeeSub("HR_HEAD_TOM", "HR_HEAD");
        hrHead.setId(2);
        hrHead.setSupervisor(ceo);

        EmployeeSub techLead = new EmployeeSub("TECH_LEAD_MIKE", "TECH_LEAD");
        techLead.setId(3);
        techLead.setSupervisor(ceo);

        hr = new EmployeeSub("HR_Marry", "HR");
        hr.setId(4);
        hr.setSupervisor(hrHead);

        engineer = new EmployeeSub("ENGINEER_M", "ENGINEER");
        developer = new EmployeeSub("DEVELOPER_A", "DEVELOPER");
        engineer.setId(5);
        developer.setId(6);
        engineer.setSupervisor(techLead);
        developer.setSupervisor(techLead);
    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }

    @Test(dataProvider = "select_employee_with_super_provider")
    public void testSelectEmployeeWithSuper(int id, EmployeeSub expectEmployee) {
        EmployeeSub employeeReturn = dao.selectEmployeeWithSuper(id);
        Assert.assertEquals(employeeReturn, expectEmployee);
    }

    @DataProvider(name = "select_employee_with_super_provider")
    public Object[][] dataProvider() {
        return new Object[][] {
                {5, engineer},
                {6, developer},
                {4, hr}
        };
    }
}
