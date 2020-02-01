package wsg.java.designpattern.strategy.member;

import wsg.java.designpattern.strategy.PriceStrategy;

/**
 * 中级会员
 *
 * @author wangsigen
 * @date 2018-10-26 16:36
 */
public class IntermediateMember implements PriceStrategy {

    @Override
    public double calcPrice(double price) {
        return price * 0.9;
    }
}
