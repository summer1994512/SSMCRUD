package com.itheima.test;

import org.junit.Test;

import java.lang.reflect.Array;

public class test {
    @Test
    public void test(){
        int n=5;
        if (n<2||n>2*Math.pow(10,5)){
            throw new RuntimeException("输入数值不符合规则");
        }
        Array[] arr = new Array[n];

    }
}
