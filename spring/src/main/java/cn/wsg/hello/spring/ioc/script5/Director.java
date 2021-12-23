package cn.wsg.hello.spring.ioc.script5;

/**
 * @author Kingen
 */
public class Director {
    public void direct() {
        Role role = new Actor();
        Script script = new Script();
        script.injectRole(role);
        script.scene();
    }
}
