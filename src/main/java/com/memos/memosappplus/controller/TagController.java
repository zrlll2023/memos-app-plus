package com.memos.memosappplus.controller;

import com.memos.memosappplus.common.Result;
import com.memos.memosappplus.entity.Tag;
import com.memos.memosappplus.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping
    public Result<Void> addTag(@RequestBody Tag tag) {
        tagService.save(tag);
        return Result.success(null);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteTag(@PathVariable Long id) {
        tagService.removeById(id);
        return Result.success(null);
    }

    @GetMapping
    public Result<List<Tag>> getAll() {
        List<Tag> tags = tagService.list();
        return Result.success(tags);
    }
}
