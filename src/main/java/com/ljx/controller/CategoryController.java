package com.ljx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljx.common.R;
import com.ljx.entity.Category;
import com.ljx.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> sava(@RequestBody Category category){
        categoryService.save(category);
        return R.success("分类添加成功");
    }

    @GetMapping("page")
    public R<Page> getPage(int page, int pageSize, String name){
        Page pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(name),Category::getName,name);
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("分类修改成功");
    }

    @DeleteMapping
    public R<String> delete(Long id){
        categoryService.removeById(id);
        return R.success("删除成功");
    }

    @GetMapping("/list")
    public R<List<Category>> getList(Category category){
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getType,category.getType());
        List<Category> categoryList = categoryService.list(queryWrapper);
        return R.success(categoryList);
    }
}
