package com.example.headlines.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.Size;

@Data
public class ArticleDTO {
    @NotBlank(message = "内容不能为空")
    @Size(max = 1000, message = "内容长度不能超过1000个字符")
    private String content;
}
