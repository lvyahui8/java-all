package org.lyh.base.proxy;

public class Calculator implements ICalculator {
    
    public double add(double num1, double num2) {
        return num1 + num2;
    }
 
    
    public double sub(double num1, double num2) {
        return num1 - num2;
    }
 
    
    public double div(double num1, double num2) {
        return num1 / num2;
    }
 
    
    public double mul(double num1, double num2) {
        return num1 * num2;
    }
}