package com.example.headlines.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ArticleVO {
    private Long id;
    private Long userId;
    private String username;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
