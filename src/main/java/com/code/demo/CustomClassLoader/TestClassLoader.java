package com.code.demo.CustomClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yan.kefei
 * @date 2018/5/24 21:55
 */
public class TestClassLoader {

    public static void main(String[] args) {
        ClassLoader c1 = TestClassLoader.class.getClassLoader();
        System.out.println(c1);
        System.out.println(c1.getParent());
        System.out.println(c1.getParent().getParent());

        DiskClassLoader diskClassLoader = new DiskClassLoader("D:\\IdeaProjects\\testClassLoader");
        try {
            Class c = diskClassLoader.loadClass("com.code.demo.CustomClassLoader.Hello");
            if (c != null) {
                Object obj = c.newInstance();
                Method method = c.getDeclaredMethod("say", null);
                method.invoke(obj, null);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
