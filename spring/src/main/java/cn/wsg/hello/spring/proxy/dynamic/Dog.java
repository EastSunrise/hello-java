package cn.wsg.hello.spring.proxy.dynamic;

/**
 * @author Kingen
 */
public class Dog implements SellAnimal {

    public void sellAnimal() {
        System.out.println("Selling dog");
    }
}
