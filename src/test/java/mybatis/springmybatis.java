package mybatis;

import cn.jonny.entity.Message;
import cn.jonny.entity.User;
import cn.jonny.mapper.Message.MessageMapper;
import cn.jonny.mapper.User.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-mybatis.xml"})
public class springmybatis {


    @Autowired
    private UserMapper userMapper;

    @Resource
    private MessageMapper messageMapper;
    private static Logger logger = LogManager.getLogger(springmybatis.class);
//    private static  ClassPathXmlApplicationContext ctx;

    @Before
    public void before(){
        logger.info("loading context");
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
    }

    @Test
    public void test() throws Exception{

        User user = userMapper.selectUser(1);
        logger.info(user);
        Message message = new Message();
        message.setId(0);
        message.setRecv("127.0.0.1:8080");
        message.setSend("127.0.0.1:8090");
        message.setMessage("hello");
        message.setStatus(1);
//        Message message1 = new Message();
//        message1.setRecv("127.0.0.1:8080");
//        message1.setSend("127.0.0.1:8090");
//        message1.setMessage("hello");
//        message1.setStatus(1);
//        List<Message> list = new ArrayList<>();
//        list.add(message);
//        list.add(message1);
//
//
//        messageMapper.insertMessageBatch(list);
        messageMapper.insertMessage(message);
//        messageMapper.queryByUserId(user).forEach(v->logger.info(v));
        logger.info("success");
    }


}
