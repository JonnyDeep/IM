package mybatis;

import cn.jonny.mapper.User.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;


public class mybatis {

    private static Logger logger = LogManager.getLogger(mybatis.class);
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
           /*
           * 方法一
           SqlSession sqlSession = sqlSessionFactory.openSession();
            User user = (User)sqlSession.selectOne("cn.jonny.mapper.UserMapper.selectUser",1);
            logger.info("success");
            logger.info(user);*/

//           userSqlSession sqlsession = sqlSessionFactory.openSession();
//           UserMapper userMapper =sqlsession.getMapper(UserMapper.class);
//           logger.info(userMapper.selectUser(1));

//           User user = new User();
//           user.setName("mybatis1");
//           user.setPassword("000000");
//           user.setPort(8091);
////           userMapper.inserUser(user);
//
//            User user1 = new User();
//            user1.setName("mybatis2");
//            user1.setPassword("000000");
//            user1.setPort(8091);
//
//            List<User> list = new ArrayList<>();
//            list.add(user);
//            list.add(user1);
//            userMapper.insertUserBash(list);
//           sqlsession.commit();
//           sqlsession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
