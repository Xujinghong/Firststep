/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

/**
 *
 * @author 49724
 */
public class ObjectTest {
    public static void main(String[] args){
        User henry = new User("henry", "123");
        User jean = new User("jean", "123");
        User stranger = jean;
        System.out.println(stranger.getName());
        stranger.setName("stranger");
        System.out.println(stranger.getName());
    }
}
