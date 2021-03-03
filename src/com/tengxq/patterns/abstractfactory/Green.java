package com.tengxq.patterns.abstractfactory;

/**
 * xxx
 *
 * @author: tengxq
 * @since: 2021-03-03 11:41
 */
public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}