package wsg.java.mybatis;

import wsg.java.mybatis.misc.Batch;
import wsg.java.mybatis.pojo.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Main class.
 *
 * @author Kingen
 */
public class Main {

    public static void main(String[] args) {
        List<User> users = new LinkedList<>();
        for (int i = 0; i < 1001; i++) {
            users.add(new User("Foreach " + i, i));
        }
        Batch.batchInsert3(users);
    }
}
