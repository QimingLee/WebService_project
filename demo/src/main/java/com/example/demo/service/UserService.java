package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public int login(String username, String password){

        String pw = userMapper.selectPasswordByPrimaryKey(username);
        if(pw == null){

            return -1;
        } else {

            if(!pw.equals(password)){

                return 0;
            }
        }
        return 1;
    }
}
