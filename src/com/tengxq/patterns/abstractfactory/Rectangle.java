package com.tengxq.patterns.abstractfactory;

/**
 * xxx
 *
 * @author: tengxq
 * @since: 2021-03-03 11:39
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
