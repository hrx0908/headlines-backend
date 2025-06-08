package com.example.headlines.controller;

import com.example.headlines.dto.ArticleDTO;
import com.example.headlines.service.ArticleService;
import com.example.headlines.vo.ArticleVO;
import com.example.headlines.vo.PageVO;
import com.example.headlines.vo.ResultVO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/article")

public class ArticleController {
    
    @Autowired
    private ArticleService articleService;
    
    @PostMapping
    public ResultVO<Boolean> publishArticle(@RequestBody @Valid ArticleDTO articleDTO, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResultVO.fail("用户未登录");
        }
        
        try {
            boolean result = articleService.publishArticle(articleDTO, userId);
            return ResultVO.success(result);
        } catch (Exception e) {
            log.error("发布文章失败", e);
            return ResultVO.fail(e.getMessage());
        }
    }
    
    @GetMapping("/list")

    public ResultVO<PageVO<ArticleVO>> getArticleList(
             @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        
        try {
            PageVO<ArticleVO> pageVO = articleService.getArticleList(pageNum, pageSize);
            return ResultVO.success(pageVO);
        } catch (Exception e) {
            log.error("获取文章列表失败", e);
            return ResultVO.fail(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")

    public ResultVO<ArticleVO> getArticleDetail(@PathVariable("id") Long articleId) {
        try {
            ArticleVO articleVO = articleService.getArticleDetail(articleId);
            return ResultVO.success(articleVO);
        } catch (Exception e) {
            log.error("获取文章详情失败", e);
            return ResultVO.fail(e.getMessage());
        }
    }
    
    @GetMapping("/my")

    public ResultVO<PageVO<ArticleVO>> getMyArticleList(
           @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            HttpSession session) {
        
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResultVO.fail("用户未登录");
        }
        
        try {
            PageVO<ArticleVO> pageVO = articleService.getMyArticleList(userId, pageNum, pageSize);
            return ResultVO.success(pageVO);
        } catch (Exception e) {
            log.error("获取我的文章列表失败", e);
            return ResultVO.fail(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResultVO<Boolean> updateArticle(
            @PathVariable("id") Long articleId,
            @RequestBody @Valid ArticleDTO articleDTO,
            HttpSession session) {
        
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResultVO.fail("用户未登录");
        }
        
        try {
            boolean result = articleService.updateArticle(articleId, articleDTO, userId);
            return ResultVO.success(result);
        } catch (Exception e) {
            log.error("修改文章失败", e);
            return ResultVO.fail(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResultVO<Boolean> deleteArticle(@PathVariable("id") Long articleId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResultVO.fail("用户未登录");
        }
        
        try {
            boolean result = articleService.deleteArticle(articleId, userId);
            return ResultVO.success(result);
        } catch (Exception e) {
            log.error("删除文章失败", e);
            return ResultVO.fail(e.getMessage());
        }
    }
}
