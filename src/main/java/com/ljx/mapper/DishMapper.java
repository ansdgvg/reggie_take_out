package com.ljx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljx.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
