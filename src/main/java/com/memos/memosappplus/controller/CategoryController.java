package com.memos.memosappplus.controller;

import com.memos.memosappplus.common.Result;
import com.memos.memosappplus.entity.Category;
import com.memos.memosappplus.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 新增
    @PostMapping
    public Result<Void> addCategory(@RequestBody Category category) {
        categoryService.save(category);
        // 大写（Category）是类名，小写（category）才是对象
        return Result.success(null);
    }

    // 删除
    @DeleteMapping("/{id}") // {id}是路径占位符
    public Result<Void> deleteCategory(@PathVariable Long id) {
        // @PathVariable 把 URL 里的 5 取出来
        categoryService.removeById(id);
        return Result.success(null);
    }

    // 查所有
    @GetMapping
    public Result<List<Category>> getAll() {
        List<Category> categories = categoryService.list();
        // List 是个类型,list才是方法
        // list() 无参,查全表
        return Result.success(categories);
    }
}
