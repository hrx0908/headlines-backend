package com.example.headlines.service;

import com.example.headlines.dto.ArticleDTO;
import com.example.headlines.vo.ArticleVO;
import com.example.headlines.vo.PageVO;

public interface ArticleService {
    
    /**
     * 发布文章
     */
    boolean publishArticle(ArticleDTO articleDTO, Long userId);
    
    /**
     * 获取文章列表（分页）
     */
    PageVO<ArticleVO> getArticleList(Integer pageNum, Integer pageSize);
    
    /**
     * 获取文章详情
     */
    ArticleVO getArticleDetail(Long articleId);
    
    /**
     * 获取我的文章列表（分页）
     */
    PageVO<ArticleVO> getMyArticleList(Long userId, Integer pageNum, Integer pageSize);
    
    /**
     * 更新文章
     */
    boolean updateArticle(Long articleId, ArticleDTO articleDTO, Long userId);
    
    /**
     * 删除文章
     */
    boolean deleteArticle(Long articleId, Long userId);
}
