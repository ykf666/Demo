package com.code.demo.Java_Reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author yan.kefei
 * @date 2018/6/21 18:04
 */
public class ReflectDemo {

    public static void main(String[] args) throws Exception {
        /**
         * 1、Class clazz = Class.forName("");
         * 2、Class clazz = obj.getClass;
         * 3、Class clazz = MyClass.class;
         */
        Class clazz = Class.forName("com.code.demo.Java_Reflect.MyClass");
        Object[] params = {"公有变量初始值", "私有变量初始值"};

        // 通过反射拿到类名
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());

        // 构造函数，通过构造函数实例化一个对象
        Constructor[] cons = clazz.getConstructors();
        Constructor con2 = cons[0];
        Class[] classPara = con2.getParameterTypes();
        Constructor con = clazz.getConstructor(classPara);
        MyClass myObject = (MyClass) con.newInstance(params);
        myObject.print();

        // 获取所有方法
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            System.out.print(method.getName() + ", ");
        }
        System.out.println();

        // 静态方法调用
        Method method = clazz.getMethod("print2", String.class);
        method.invoke(null, "hello print2");

        // 私有方法调用
        Method method1 = clazz.getDeclaredMethod("print1");
        method1.setAccessible(true);
        method1.invoke(myObject);

        // 获取声明的类变量，包括private修饰的变量，但不包括父类声明的变量
        // getFields()获取类public修饰的变量
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.getModifiers() == Modifier.PUBLIC) {
                System.out.println(field.getName() + ": " + field.get(myObject));
                if (field.getType() == String.class) {
                    field.set(myObject, "公有变量值2");
                }
            } else {
                // 访问私有变量
                field.setAccessible(true);
                System.out.println(field.getName() + ": " + field.get(myObject));
            }
        }
        myObject.print();

        // 获取注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof MyAnnotation){
                MyAnnotation anno = (MyAnnotation) annotation;
                System.out.println(anno.value());
            }
        }

        // 泛型
        Method method2 = clazz.getMethod("getStringList");
        Type returnType = method2.getGenericReturnType();
        if (returnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] types = type.getActualTypeArguments();
            for (Type t : types) {
                System.out.println(t.getTypeName());
            }
        }

    }
}
