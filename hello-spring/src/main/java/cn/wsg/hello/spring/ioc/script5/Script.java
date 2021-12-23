package cn.wsg.hello.spring.ioc.script5;

/**
 * @author Kingen
 */
public class Script implements ActorArrangeable {

    private Role role;

    public void injectRole(Role role) {
        this.role = role;
    }

    public void scene() {
        role.play();
    }
}
