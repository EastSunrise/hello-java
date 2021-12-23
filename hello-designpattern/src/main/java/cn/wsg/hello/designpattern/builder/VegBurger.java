package cn.wsg.hello.designpattern.builder;

public class VegBurger extends Burger {

    @Override
    public String name() {
        return "Veg Burger.";
    }

    @Override
    public float price() {
        return 26.0f;
    }
}
