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
public class HelloWorld {
    
    static{
        System.out.println("Hello  from static");
    }
    
    public HelloWorld(){
        System.out.println("Hello from Constructor");
    }
    
    public void sayHello(){
        System.out.println("Hello from method");
    }
    
    public static void main(String[] args){
        System.out.println("Hello from main");
        new HelloWorld().sayHello();
    }
    
}
