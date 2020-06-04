package wsg.java.mybatis.mapper;

import wsg.java.mybatis.pojo.User;

import java.util.List;

/**
 * Mapper interface for example.
 *
 * @author Kingen
 */
public interface UserMapper {

    User selectUser(String username);

    int insertUser(User user);

    int batchInsert(List<User> users);
}
