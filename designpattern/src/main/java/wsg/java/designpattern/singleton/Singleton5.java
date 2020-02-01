package wsg.java.designpattern.singleton;

public class Singleton5 {
    private static class SingletonLoader{
        private static final Singleton5 INSTANCE=new Singleton5();
    }
    private Singleton5(){}

    private static final Singleton5 getInstance(){
        return SingletonLoader.INSTANCE;
    }
}
