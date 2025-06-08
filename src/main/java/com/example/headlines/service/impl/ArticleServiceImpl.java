package com.example.headlines.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.headlines.dto.ArticleDTO;
import com.example.headlines.entity.Article;
import com.example.headlines.mapper.ArticleMapper;
import com.example.headlines.service.ArticleService;
import com.example.headlines.service.UserService;
import com.example.headlines.vo.ArticleVO;
import com.example.headlines.vo.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    
    @Autowired
    private UserService userService;
    
    @Override
    public boolean publishArticle(ArticleDTO articleDTO, Long userId) {
        Article article = new Article();
        article.setContent(articleDTO.getContent());
        article.setUserId(userId);
        return this.save(article);
    }
    
    @Override
    public PageVO<ArticleVO> getArticleList(Integer pageNum, Integer pageSize) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        Page<Article> articlePage = baseMapper.selectArticleListWithUsername(page);
        
        return convertToPageVO(articlePage);
    }
    
    @Override
    public ArticleVO getArticleDetail(Long articleId) {
        Article article = baseMapper.selectById(articleId);
        if (article == null) {
            throw new RuntimeException("文章不存在");
        }
        
        // 获取作者用户名
        article.setUsername(userService.getUserById(article.getUserId()).getUsername());
        
        return convertToArticleVO(article);
    }
    
    @Override
    public PageVO<ArticleVO> getMyArticleList(Long userId, Integer pageNum, Integer pageSize) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        Page<Article> articlePage = baseMapper.selectArticleListByUserId(page, userId);
        
        return convertToPageVO(articlePage);
    }
    
    @Override
    public boolean updateArticle(Long articleId, ArticleDTO articleDTO, Long userId) {
        // 先查询文章是否存在
        Article article = baseMapper.selectById(articleId);
        if (article == null) {
            throw new RuntimeException("文章不存在");
        }
        
        // 检查是否是文章作者
        if (!article.getUserId().equals(userId)) {
            throw new RuntimeException("无权修改他人文章");
        }
        
        // 更新文章内容
        article.setContent(articleDTO.getContent());
        return this.updateById(article);
    }
    
    @Override
    public boolean deleteArticle(Long articleId, Long userId) {
        // 先查询文章是否存在
        Article article = baseMapper.selectById(articleId);
        if (article == null) {
            throw new RuntimeException("文章不存在");
        }
        
        // 检查是否是文章作者
        if (!article.getUserId().equals(userId)) {
            throw new RuntimeException("无权删除他人文章");
        }
        
        // 逻辑删除文章
        return this.removeById(articleId);
    }
    
    private ArticleVO convertToArticleVO(Article article) {
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article, articleVO);
        return articleVO;
    }
    
    private PageVO<ArticleVO> convertToPageVO(Page<Article> page) {
        List<ArticleVO> articleVOList = new ArrayList<>();
        for (Article article : page.getRecords()) {
            articleVOList.add(convertToArticleVO(article));
        }
        
        return new PageVO<>(
                articleVOList,
                page.getTotal(),
                page.getSize(),
                page.getCurrent()
        );
    }
}
