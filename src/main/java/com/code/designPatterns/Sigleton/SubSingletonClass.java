package com.code.designPatterns.Sigleton;

/**
 * Created by yankefei on 2020/11/30.
 */
public class SubSingletonClass {

    private String value;

    public SubSingletonClass(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toHexString(hashCode()) + ", value=" + this.value;
    }
}
