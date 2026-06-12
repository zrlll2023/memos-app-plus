package com.memos.memosappplus.controller;

import com.memos.memosappplus.common.Result;
import com.memos.memosappplus.entity.Article;
import com.memos.memosappplus.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @DeleteMapping("/{id}")
    public Result<Void> deleteArticle(@PathVariable Long id) {
        articleService.removeById(id);
        return Result.success(null);
    }


    @GetMapping("/{id}")  // 通过id查找文章
    public Result<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.getById(id); // 返回的是一篇文章,类型是Article
        return Result.success(article);
    }
}
