package com.tengxq.patterns.abstractfactory;

/**
 * xxx
 *
 * @author: tengxq
 * @since: 2021-03-03 11:40
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
