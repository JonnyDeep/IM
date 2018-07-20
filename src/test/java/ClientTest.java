import cn.jonny.GlobalStaticVariable;
import cn.jonny.client.client;
import cn.jonny.entity.User;
import cn.jonny.utils.DBUtils;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.statements.SpringRepeat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientTest {

    private static Logger logger  = LogManager.getLogger("mylog");
//    private static Logger logger = LogManager.getLogger(ClientTest.class);

    @Test
    public void test1() throws IOException,SQLException,ClassNotFoundException
    {
        Connection con = DBUtils.getCon();
        if(con!=null){
            System.out.println("connect successed");
        }
        String sql = "SELECT * FROM imdb.usertable;";
        List<User> ls = DBUtils.Select(sql,con);

        List<String> ls1 = DBUtils.SelectList(sql,con);

        for (User user: ls
             ) {
            System.out.println(user);
        }

        for (String str:
             ls1) {
            System.out.println(str);
            System.out.println(new Gson().fromJson(str,User.class));
        }
        DBUtils.close();
    }

    @Test
    public void test2()
    {
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug" );
        logger.trace("trace");
        logger.fatal("fata");
        try {
            TimeUnit.SECONDS.sleep(61);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug" );
        logger.trace("trace");
        logger.fatal("fata");
    }



}
