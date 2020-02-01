package wsg.java.designpattern.strategy.member;

import wsg.java.designpattern.strategy.PriceStrategy;

/**
 * wsg
 *
 * @author wangsigen
 * @date 2018-10-26 16:37
 */
public class AdvancedMember implements PriceStrategy {
    @Override
    public double calcPrice(double price) {
        return price * 0.8;
    }
}
