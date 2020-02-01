package wsg.java.designpattern.builder;

public class Coke extends Drink {
    @Override
    public String name() {
        return "Coke.";
    }

    @Override
    public float price() {
        return 10.0f;
    }
}
