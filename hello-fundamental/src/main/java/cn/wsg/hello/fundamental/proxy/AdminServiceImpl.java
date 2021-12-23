package cn.wsg.hello.fundamental.proxy;

/**
 * Implementation of the interface
 *
 * @author Kingen
 */
public class AdminServiceImpl implements AdminService {

    @Override
    public void update(String s) {
        System.out.println("Updating...");
    }

    @Override
    public String get() {
        System.out.println("Getting...");
        return "Getting...";
    }
}
