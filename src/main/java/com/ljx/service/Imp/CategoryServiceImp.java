package com.ljx.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljx.entity.Category;
import com.ljx.mapper.CategoryMapper;
import com.ljx.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
