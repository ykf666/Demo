package com.code.lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * Created by yankefei on 2018/11/27.
 * 函数替代接口示例
 */
public class MoneyDemo {

    public static void main(String[] args) {
        MyMoney me = new MyMoney(333335555);
        me.printMoney(i -> new DecimalFormat("#,###").format(i));

        me.printMoney2(i -> new DecimalFormat("#,###").format(i));
    }

}

interface IMoneyFormat {
    String format(int i);
}

class MyMoney {
    private final int money;

    public MyMoney(int money) {
        this.money = money;
    }

    public void printMoney(IMoneyFormat moneyFormat) {
        System.out.println("我的存款为：" + moneyFormat.format(this.money));
    }

    public void printMoney2(Function<Integer, String> moneyFormat) {
        System.out.println("我的存款为：" + moneyFormat.apply(this.money));
    }
}
