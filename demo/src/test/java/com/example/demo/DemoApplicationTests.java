package com.example.demo;

import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){

        System.out.println(userMapper.selectPasswordByPrimaryKey("1"));
        System.out.println(userMapper.selectPasswordByPrimaryKey("4"));
    }

}
