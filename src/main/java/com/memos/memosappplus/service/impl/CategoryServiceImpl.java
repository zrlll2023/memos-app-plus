package com.memos.memosappplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memos.memosappplus.entity.Category;
import com.memos.memosappplus.mapper.CategoryMapper;
import com.memos.memosappplus.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

}
