package wsg.java.spring.ioc.script3;

/**
 * Inject by the constructor.
 *
 * @author Kingen
 */
public class Script {
    private Role role;

    // Inject the actor of the role.
    public Script(Role role) {
        this.role = role;
    }

    public void scene() {
        role.play();
    }
}
