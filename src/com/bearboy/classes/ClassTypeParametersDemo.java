package com.bearboy.classes;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.stream.Stream;

/**
 * 通过ParameterizedType 来获取泛型具体类型
 * 案例通过获取Dog对应泛型类型
 */
public class ClassTypeParametersDemo {

    public static void main(String[] args) {
        Dog dog = new Dog();
        System.out.println(Animal.class.isAssignableFrom(Dog.class));
        Type[] types = Dog.class.getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] paramterized = parameterizedType.getActualTypeArguments();
                Stream.of(paramterized).forEach(System.out::println);
            }
        }
        Stream.of(Animal.class.getTypeParameters()).forEach(System.out::println);
        Type[] params = Animal.class.getTypeParameters();
        if (params.length !=0){
            System.out.println(ParameterizedTypeImpl.make(
                    Animal.class, params, Animal.class.getEnclosingClass()));
        }

    }
}

interface Animal<T> {
    T getName();
}

class Dog implements Animal<String> {

    @Override
    public String getName() {
        return "Dog";
    }
}
