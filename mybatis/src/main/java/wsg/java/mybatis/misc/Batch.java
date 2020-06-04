package wsg.java.mybatis.misc;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import wsg.java.mybatis.configuration.MyConfiguration;
import wsg.java.mybatis.mapper.UserMapper;
import wsg.java.mybatis.pojo.User;

import java.util.List;

/**
 * Batch insert or update records.
 *
 * @author Kingen
 */
public class Batch {

    public static int batchInsert(List<User> users) {
        SqlSession sqlSession = MyConfiguration.getSqlSessionFactory().openSession(false);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = 0;
        for (User user : users) {
            count += mapper.insertUser(user);
        }
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    public static int batchInsert2(List<User> users) {
        SqlSession sqlSession = MyConfiguration.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = 0;
        for (User user : users) {
            count += mapper.insertUser(user);
        }
        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    public static int batchInsert3(List<User> users) {
        if (users != null && users.size() > 0) {
            SqlSession sqlSession = MyConfiguration.getSqlSessionFactory().openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int count = mapper.batchInsert(users);
            sqlSession.commit();
            sqlSession.close();
            return count;
        }

        return 0;
    }
}
