package com.memos.memosappplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.memos.memosappplus.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
