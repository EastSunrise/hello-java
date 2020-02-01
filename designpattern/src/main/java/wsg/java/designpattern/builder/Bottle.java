package wsg.java.designpattern.builder;

public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottled";
    }
}
