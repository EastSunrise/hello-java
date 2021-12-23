package cn.wsg.hello.spring.proxy.state;

/**
 * @author Kingen
 */
public class RealMovie implements Movie {

    public void play() {
        System.out.println("Watching movie");
    }
}
