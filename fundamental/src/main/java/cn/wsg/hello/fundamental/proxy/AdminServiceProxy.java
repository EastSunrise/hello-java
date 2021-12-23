package cn.wsg.hello.fundamental.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Proxy of the interface
 *
 * @author Kingen
 */
public class AdminServiceProxy {

    private final Object target;
    private final InvocationHandler handler;

    public AdminServiceProxy(Object target, InvocationHandler handler) {
        this.target = target;
        this.handler = handler;
    }

    public static void main(String[] args) {
        AdminService adminService = new AdminServiceImpl();
        AdminServiceInvocation invocation = new AdminServiceInvocation(adminService);
        AdminService proxy = (AdminService) new AdminServiceProxy(adminService, invocation)
            .getProxy();
        proxy.update("ss");
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }
}
