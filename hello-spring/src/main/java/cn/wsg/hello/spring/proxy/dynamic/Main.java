package cn.wsg.hello.spring.proxy.dynamic;

/**
 * @author Kingen
 */
public class Main {

    public static void main(String[] args) {
        Shop shop = new Shop(new Apple());
    }
}
