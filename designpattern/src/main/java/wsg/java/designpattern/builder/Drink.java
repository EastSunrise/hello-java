package wsg.java.designpattern.builder;

public abstract class Drink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }
}
