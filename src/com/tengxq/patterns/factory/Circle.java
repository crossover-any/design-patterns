package com.tengxq.patterns.factory;

/**
 * xxx
 *
 * @author: tengxq
 * @since: 2021-03-02 17:01
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
