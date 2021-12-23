package cn.wsg.hello.spring.ioc.script3;

/**
 * Inject into the script.
 *
 * @author Kingen
 */
public class Director {
    public void direct() {
        Role role = new Actor();
        Script script = new Script(role);
        script.scene();
    }
}
