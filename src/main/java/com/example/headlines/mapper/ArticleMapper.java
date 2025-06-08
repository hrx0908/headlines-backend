package com.example.headlines.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.headlines.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    
    /**
     * 分页查询文章列表，并关联用户名
     */
    Page<Article> selectArticleListWithUsername(Page<Article> page);
    
    /**
     * 根据用户ID分页查询文章列表
     */
    Page<Article> selectArticleListByUserId(Page<Article> page, @Param("userId") Long userId);
}
