package com.example.headlines.service;

import com.example.headlines.dto.LoginDTO;
import com.example.headlines.dto.RegisterDTO;
import com.example.headlines.vo.UserVO;

public interface UserService {
    
    /**
     * 用户注册
     */
    boolean register(RegisterDTO registerDTO);
    
    /**
     * 用户登录
     */
    UserVO login(LoginDTO loginDTO);
    
    /**
     * 根据用户ID获取用户信息
     */
    UserVO getUserById(Long userId);
}
