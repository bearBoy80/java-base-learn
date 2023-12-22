package com.bearboy.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyVetoException;
import java.util.Arrays;

public class PersonBeanDemo {
    public static void main(String[] args) throws IntrospectionException, PropertyVetoException {
        Person person = new Person();
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        Arrays.stream(beanInfo.getPropertyDescriptors())
                .forEach(x-> System.out.println("Person 类对应的属性：" + x.getName()));
        Arrays.stream(beanInfo.getMethodDescriptors())
                .forEach(x-> System.out.println("Person 类对应的方法：" +x.getName()));
        person.addPropertyChangeListener(event->{
            // 属性变化通知事件
            System.out.printf("监听到属性[%s] 内容变化（事件来源：%s），老值：%s，新值：%s\n",
                    event.getPropertyName(),
                    event.getSource(),
                    event.getOldValue(),
                    event.getNewValue()
            );
        });
        person.addVetoableChangeListener(event -> {
            System.out.printf("Vetoable监听到属性[%s] 内容变化（事件来源：%s），老值：%s，新值：%s\n",
                    event.getPropertyName(),
                    event.getSource(),
                    event.getOldValue(),
                    event.getNewValue()
            );
        });
        person.setName("jacktao");
    }
}
