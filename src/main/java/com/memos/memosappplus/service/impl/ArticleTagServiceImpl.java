package com.memos.memosappplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memos.memosappplus.entity.ArticleTag;
import com.memos.memosappplus.mapper.ArticleTagMapper;
import com.memos.memosappplus.service.ArticleTagService;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
        implements ArticleTagService {
}
