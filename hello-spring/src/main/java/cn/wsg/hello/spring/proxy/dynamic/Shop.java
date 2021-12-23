package cn.wsg.hello.spring.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Kingen
 */
public class Shop implements InvocationHandler {

    private final Object object;

    public Shop(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(object, args);
        return null;
    }
}
