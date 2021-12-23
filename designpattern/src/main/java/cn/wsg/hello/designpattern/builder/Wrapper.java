package cn.wsg.hello.designpattern.builder;

public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapped";
    }
}
