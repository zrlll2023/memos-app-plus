package com.memos.memosappplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.memos.memosappplus.entity.Comment;
import com.memos.memosappplus.mapper.CommentMapper;
import com.memos.memosappplus.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper , Comment>
        implements CommentService {

}
