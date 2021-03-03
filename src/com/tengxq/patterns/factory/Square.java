package com.tengxq.patterns.factory;

/**
 * xxx
 *
 * @author: tengxq
 * @since: 2021-03-02 17:00
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
