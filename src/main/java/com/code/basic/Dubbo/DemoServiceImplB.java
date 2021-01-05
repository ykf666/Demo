package com.code.basic.Dubbo;

/**
 * @author yan.kefei
 * @date 2018/6/26 16:35
 */
public class DemoServiceImplB implements DemoService {

    @Override
    public String getName(int id) {
        return "DemoServiceImplB_" + id;
    }
}
