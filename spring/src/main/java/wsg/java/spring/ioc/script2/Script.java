package wsg.java.spring.ioc.script2;

/**
 * Use interface to separate the actor and the role.
 * Script and the actor are still related.
 *
 * @author Kingen
 */
public class Script {
    public void scene() {
        Role role = new Actor();
        role.play();
    }
}
