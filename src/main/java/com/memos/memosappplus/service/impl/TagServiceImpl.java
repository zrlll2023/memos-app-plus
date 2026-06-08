package com.memos.memosappplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memos.memosappplus.entity.Tag;
import com.memos.memosappplus.mapper.TagMapper;
import com.memos.memosappplus.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {

}
