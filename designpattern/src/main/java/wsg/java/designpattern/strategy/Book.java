package wsg.java.designpattern.strategy;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2018-10-26 16:40
 */
public class Book {

    private double price;

    public double getDiscountPrice(PriceStrategy priceStrategy) {
        return priceStrategy.calcPrice(price);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
