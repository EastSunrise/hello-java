package cn.wsg.hello.spring.ioc.script4;

/**
 * Inject into the script.
 *
 * @author Kingen
 */
public class Director {
    public void direct() {
        Role role = new Actor();
        Script script = new Script();
        script.setRole(role);
        script.scene();
    }
}
