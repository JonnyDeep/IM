package mybatis;

import cn.jonny.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-mybatis.xml"})
public class springmybatis {


    @Autowired
    private UserMapper userMapper;

    private static Logger logger = LogManager.getLogger(springmybatis.class);
//    private static  ClassPathXmlApplicationContext ctx;

    @Before
    public void before(){
        logger.info("loading context");
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
    }

    @Test
    public void test(){
        logger.info(userMapper.selectUser(1));

        logger.info("success");
    }


}
