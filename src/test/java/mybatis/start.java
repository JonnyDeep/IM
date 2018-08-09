package mybatis;

import cn.jonny.GlobalStaticVariable;
import cn.jonny.Server.ServerListen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class start {
    private static Logger logger = LogManager.getLogger(start.class);
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        logger.info("loading conttext success");
        ServerListen s = (ServerListen) ctx.getBean("serverListen");
        new Thread(s).start();
//        try {
//            s = new ServerListen(GlobalStaticVariable.PORT);
//            Thread listen = new Thread(s);
//            listen.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
