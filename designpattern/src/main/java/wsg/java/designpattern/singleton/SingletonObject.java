package wsg.java.designpattern.singleton;

public class SingletonObject {
    private volatile static SingletonObject instance;
    private SingletonObject(){}

    public static SingletonObject getInstance(){
        if(instance==null){
            synchronized (SingletonObject.class){
                if(instance==null){
                    instance=new SingletonObject();
                }
            }
        }
        return instance;
    }
}