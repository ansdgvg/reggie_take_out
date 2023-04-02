package com.ljx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljx.dto.DishDto;
import com.ljx.entity.Dish;

public interface DishService extends IService<Dish> {
    public void saveDish(DishDto dishDto);
    public DishDto echoPageData(Long id);
    public void updateDish(DishDto dishDto);
}
