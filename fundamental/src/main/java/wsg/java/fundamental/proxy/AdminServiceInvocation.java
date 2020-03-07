package wsg.java.fundamental.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Invocation for the interface
 *
 * @author Kingen
 */
public class AdminServiceInvocation implements InvocationHandler {

    private Object target;

    public AdminServiceInvocation(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("PreHandling...");
        return method.invoke(target, args);
    }
}
