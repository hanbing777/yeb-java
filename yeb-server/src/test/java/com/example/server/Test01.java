package com.example.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @PROJECT_NAME: yeb-java
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/9/12 0012 9:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {

    @Test
    public void test01(){
        String s="12345";
        System.out.println(s.substring(1));

    }
}