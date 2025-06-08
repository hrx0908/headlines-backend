package com.example.headlines.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.headlines.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
