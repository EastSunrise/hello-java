package wsg.java.designpattern.strategy.member;

import wsg.java.designpattern.strategy.PriceStrategy;

/**
 * 初级会员
 *
 * @author wangsigen
 * @date 2018-10-26 16:34
 */
public class PrimaryMember implements PriceStrategy {

    @Override
    public double calcPrice(double price) {
        return price;
    }
}
