package com.memos.memosappplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memos.memosappplus.entity.Article;
import com.memos.memosappplus.mapper.ArticleMapper;
import com.memos.memosappplus.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

}
