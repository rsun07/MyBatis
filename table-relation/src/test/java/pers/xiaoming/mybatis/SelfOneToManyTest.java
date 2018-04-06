package pers.xiaoming.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pers.xiaoming.mybatis.dao.SelfOneToManyDao;
import pers.xiaoming.mybatis.dao.SessionManager;
import pers.xiaoming.mybatis.entity.one_to_many.City;
import pers.xiaoming.mybatis.entity.one_to_many.Person;
import pers.xiaoming.mybatis.entity.self_relation.EmployeeSuper;

import java.util.HashSet;
import java.util.Set;

public class SelfOneToManyTest {
    private static SqlSession session;
    private static SelfOneToManyDao dao;

    private static EmployeeSuper ceo;

    /*
         Pre inserted data:

         INSERT INTO employee_super (name, title) VALUES ("CEO_JOHN", "CEO");

         INSERT INTO employee_super (name, title, manager_id) VALUES ("HR_HEAD_TOM", "HR_HEAD", 1);
         INSERT INTO employee_super (name, title, manager_id) VALUES ("TECH_LEAD_MIKE", "TECH_LEAD", 1);

         INSERT INTO employee_super (name, title, manager_id) VALUES ("HR_Marry", "HR", 2);
         INSERT INTO employee_super (name, title, manager_id) VALUES ("ENGINEER_M", "ENGINEER", 3);
         INSERT INTO employee_super (name, title, manager_id) VALUES ("DEVELOPER_A", "DEVELOPER", 3);


     */

    @BeforeClass
    public static void setup() {
        session = SessionManager.getSession();
        dao = session.getMapper(SelfOneToManyDao.class);

        ceo = new EmployeeSuper("CEO_JOHN", "CEO");
        ceo.setId(1);

        EmployeeSuper hrHead = new EmployeeSuper("HR_HEAD_TOM", "HR_HEAD");
        hrHead.setId(2);
        EmployeeSuper techLead = new EmployeeSuper("TECH_LEAD_MIKE", "TECH_LEAD");
        techLead.setId(3);

        ceo.getSubordinators().add(hrHead);
        ceo.getSubordinators().add(techLead);

        EmployeeSuper hr = new EmployeeSuper("HR_Marry", "HR");
        hr.setId(4);
        hrHead.getSubordinators().add(hr);

        EmployeeSuper engineer = new EmployeeSuper("ENGINEER_M", "ENGINEER");
        EmployeeSuper developer = new EmployeeSuper("DEVELOPER_A", "DEVELOPER");
        engineer.setId(5);
        developer.setId(6);
        techLead.getSubordinators().add(engineer);
        techLead.getSubordinators().add(developer);

    }

    @AfterClass
    public static void clean() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void test() {
        EmployeeSuper ceoReturn = dao.selectEmployeeWithSubs(1);
        Assert.assertEquals(ceoReturn, ceo);
    }
}
