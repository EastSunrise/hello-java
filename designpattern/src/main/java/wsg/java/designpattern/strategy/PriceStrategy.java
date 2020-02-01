package wsg.java.designpattern.strategy;

/**
 * 抽象折扣类
 *
 * @author wangsigen
 * @date 2018-10-26 16:33
 */
public interface PriceStrategy {

    double calcPrice(double price);
}
