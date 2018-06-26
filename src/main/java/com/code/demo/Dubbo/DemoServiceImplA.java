package com.code.demo.Dubbo;

/**
 * @author yan.kefei
 * @date 2018/6/25 16:40
 */
public class DemoServiceImplA implements DemoService {

    @Override
    public String getName(int id) {
        return "DemoServiceImplA_" + id;
    }
}
