package wsg.java.designpattern.builder;

public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapped";
    }
}
