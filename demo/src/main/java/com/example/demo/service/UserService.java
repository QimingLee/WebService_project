package com.example.demo.service;

import com.example.demo.entity.User;
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
    
    public boolean hasUser(String username) {
        return userMapper.selectByPrimaryKey(username).getUsername() != null;
    }

    public int register(String username, String password){
        String pw = userMapper.selectPasswordByPrimaryKey(username);
        if(pw == null){
            User user_new = new User();
            user_new.setUsername(username);
            user_new.setPassword(password);
            userMapper.insert(user_new);
            return 1;
        } else {
            return 0;
        }
    }
}
