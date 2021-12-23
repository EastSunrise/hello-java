package cn.wsg.hello.spring.ioc.script1;

/**
 * Common solution, calling the actor in the script directly.
 *
 * @author Kingen
 */
public class Script {

    public void scene() {
        Actor actor = new Actor();
        actor.play();
    }
}
