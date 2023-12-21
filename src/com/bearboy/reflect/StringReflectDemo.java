package com.bearboy.reflect;

import java.lang.reflect.Field;

/**
 * 通过反射将string的值动态调整为特定值
 */
public class StringReflectDemo {
    public static void main(String[] args) throws Exception {
        String oldName = "old";
        String newName ="new";
        Field valueField = String.class.getDeclaredField("value");
        System.out.println(valueField.getGenericType().getTypeName());
        valueField.setAccessible(true);
        valueField.set(oldName,newName.getBytes());
        System.out.println(oldName);

    }
}
