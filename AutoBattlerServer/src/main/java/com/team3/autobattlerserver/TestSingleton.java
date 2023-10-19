/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package com.team3.autobattlerserver;

/**
 *
 * @author Rio
 */
public class TestSingleton {
    
    private TestSingleton() {
    }
    
    public static TestSingleton getInstance() {
        return TestSingletonHolder.INSTANCE;
    }
    
    private static class TestSingletonHolder {

        private static final TestSingleton INSTANCE = new TestSingleton();
    }
}
