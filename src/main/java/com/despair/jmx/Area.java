package com.despair.jmx;

public class Area implements AreaMBean {
    @Override
    public double calculateArea(double radius) {
        double q1 = Math.PI * radius * radius / 4;
        double q2 = radius * radius / 2;
        double q3 = radius * radius / 2;
        double q4 = 0;
        return q1 + q2 + q3 + q4;
    }
}
