package com.bearboy.beans;

import java.beans.*;

public class Person {

    private final transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private final transient VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);
    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) throws PropertyVetoException {
        String propertyName = "name";

        String oldValue = this.name; // 读取老值
        String newValue = name; // 修改后值(newValue)
        // 勉强属性（Constrained properties）必须在更新前执行
        // 校验规则：当名称为纯数字时，阻断更新
        // 当 PropertyVetoException 异常发生时
        vetoableChangeSupport.fireVetoableChange(propertyName, oldValue, newValue);

        this.name = name; // this.name
        // 发布属性已经变化事件 - PropertyChangeEvent
        // 强迫属性（Bound Properties）：当属性变化时，强制更新并且发送通知属性变化通知事件
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        this.vetoableChangeSupport.addVetoableChangeListener(listener);
    }
}
