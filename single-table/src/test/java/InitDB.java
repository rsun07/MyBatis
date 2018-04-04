import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeSuite;
import pers.xiaoming.mybatis.dao.SessionManager;

public class InitDB {

    @BeforeSuite
    public static void InitDB() {
        try (SqlSession session = SessionManager.getSession()) {
            session.delete("truncateStudentTable");
        }
    }

}
