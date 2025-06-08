package com.example.headlines.controller;

import com.example.headlines.dto.LoginDTO;
import com.example.headlines.dto.RegisterDTO;
import com.example.headlines.service.UserService;
import com.example.headlines.vo.ResultVO;
import com.example.headlines.vo.UserVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping("/user")

public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResultVO<Boolean> register(@RequestBody @Valid RegisterDTO registerDTO) {
        try {
            boolean result = userService.register(registerDTO);
            return ResultVO.success(result);
        } catch (Exception e) {
            log.error("注册失败", e);
            return ResultVO.fail(e.getMessage());
        }
    }
    
    @PostMapping("/login")

    public ResultVO<UserVO> login(@RequestBody @Valid LoginDTO loginDTO, HttpSession session) {
        try {
            UserVO userVO = userService.login(loginDTO);
            // 将用户ID存入session
            session.setAttribute("userId", userVO.getId());
            return ResultVO.success(userVO);
        } catch (Exception e) {
            log.error("登录失败", e);
            return ResultVO.fail(e.getMessage());
        }
    }
    
    @PostMapping("/logout")

    public ResultVO<Boolean> logout(HttpSession session) {
        // 清除session
        session.removeAttribute("userId");
        return ResultVO.success(true);
    }
    
    @GetMapping("/info")

    public ResultVO<UserVO> getUserInfo(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResultVO.fail("用户未登录");
        }
        
        try {
            UserVO userVO = userService.getUserById(userId);
            return ResultVO.success(userVO);
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return ResultVO.fail(e.getMessage());
        }
    }
}
