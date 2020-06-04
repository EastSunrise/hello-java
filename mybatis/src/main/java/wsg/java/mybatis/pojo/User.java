package wsg.java.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * POJO for user.
 *
 * @author Kingen
 */
@Data
@AllArgsConstructor
public class User {

    private String username;

    private Integer age;
}
