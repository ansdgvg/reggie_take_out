package com.ljx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljx.common.R;
import com.ljx.dto.SetMealDto;
import com.ljx.entity.Category;
import com.ljx.entity.SetMeal;
import com.ljx.service.CategoryService;
import com.ljx.service.SetMealService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Autowired
    private SetMealService setMealService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public R<Page> getPage(int page, int pageSize, String name){
        Page<SetMeal> setMealPage = new Page<>(page, pageSize);
        Page<SetMealDto> setMealDtoPage = new Page<>();

        LambdaQueryWrapper<SetMeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(name),SetMeal::getName,name);
        queryWrapper.orderByDesc(SetMeal::getUpdateTime);
        setMealService.page(setMealPage,queryWrapper);

        BeanUtils.copyProperties(setMealPage,setMealDtoPage,"records");

        List<SetMeal> setMealPageRecords = setMealPage.getRecords();
        List<SetMealDto> setMealDtoList = setMealPageRecords.stream().map((item) -> {
            SetMealDto setMealDto = new SetMealDto();
            BeanUtils.copyProperties(item, setMealDto);

            Category category = categoryService.getById(item.getCategoryId());
            String categoryName = category.getName();
            setMealDto.setCategoryName(categoryName);

            return setMealDto;
        }).collect(Collectors.toList());

        setMealDtoPage.setRecords(setMealDtoList);
        return R.success(setMealDtoPage);
    }
}
