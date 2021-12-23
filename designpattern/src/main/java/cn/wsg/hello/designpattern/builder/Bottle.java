package cn.wsg.hello.designpattern.builder;

public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottled";
    }
}
