package cn.wsg.hello.spring.ioc.script4;

/**
 * Inject by the setter method of the property.
 *
 * @author Kingen
 */
public class Script {
    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }

    public void scene() {
        role.play();
    }
}
