package com.bearboy.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

/**
 *          Java 类成员对象来自于 java.lang.Class
 *          成员访问性
 *          所有成员 getDeclaredXXX() ->
 *          getDeclaredMethods()
 *          getDeclaredFields()
 *          getDeclaredConstructors
 */
public class ClassMemberDemo {
    public static void main(String[] args) {

        //获取当前类所有声明的字段
        Field[] declaredFields = ExtendedData.class.getDeclaredFields();
        //获取当前类public 的字段
        Field[] fields = ExtendedData.class.getFields();
        //获取当前类public 的方法
        Method[] methods = ExtendedData.class.getMethods();
        //获取类所有public 方法
        Method[] declaredMethods = ExtendedData.class.getDeclaredMethods();

        List<Field> allDeclaredFields = getAllDeclaredFields(ExtendedData.class);

        System.out.println("ExtendedData 类所有层次的声明字段 : " + allDeclaredFields.stream().map(Field::getName).collect(Collectors.joining(",")));

        System.out.println("ExtendedData 类所有的声明字段 : " + Stream.of(declaredFields).map(Field::getName).collect(Collectors.joining(",")));
        System.out.println("ExtendedData 类所有的 public 字段 : " + Stream.of(fields).map(Field::getName).collect(Collectors.joining(",")));

        System.out.println("ExtendedData 类所有的声明方法 : " + Stream.of(declaredMethods).map(Method::getName).collect(Collectors.joining(",")));
        System.out.println("ExtendedData 类所有的 public 方法 : " + Stream.of(methods).map(Method::getName).collect(Collectors.joining(",")));

    }

    static List<Field> getAllDeclaredFields(Class<?> type) {
        if (Object.class.equals(type)) {
            return Collections.emptyList();
        }
        List<Field> allDeclaredFields = new LinkedList<>();
        Field[] declaredFields = type.getDeclaredFields();
        allDeclaredFields.addAll(asList(declaredFields));
        Class<?> superClass = type.getSuperclass();

        while (true) {
            if (superClass == null || Object.class.equals(superClass)) {
                break;
            }
            allDeclaredFields.addAll(getAllDeclaredFields(superClass));
            superClass = superClass.getSuperclass();
        }

        return allDeclaredFields;
    }
}
class Data {

    private int value;

    public int getValue() {
        return value;
    }

    protected void setValue(int value) {
        this.value = value;
    }
}

class ExtendedData extends Data {

    private String name;

    public String alise;

    public void setName(String name) {
        this.name = name;
    }

    public void setAlise(String alise) {
        this.alise = alise;
    }
}