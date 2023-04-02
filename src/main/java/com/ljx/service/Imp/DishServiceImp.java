package com.ljx.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljx.dto.DishDto;
import com.ljx.entity.Dish;
import com.ljx.entity.DishFlavor;
import com.ljx.mapper.DishMapper;
import com.ljx.service.DishFlavorService;
import com.ljx.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImp extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品，同时保存菜品的口味数据，需要同时操作两张表
     * @param dishDto
     */
    @Override
    public void saveDish(DishDto dishDto) {

        this.save(dishDto);

        Long dishId = dishDto.getId();
        //给DishFlavor表中dishId赋值
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品口味数据到dish_flavor表
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 回显修改菜品页面数据
     * @param id
     * @return
     */
    @Override
    public DishDto echoPageData(Long id) {
        DishDto dishDto = new DishDto();
        Dish dish = this.getById(id);

        //对象拷贝
        BeanUtils.copyProperties(dish,dishDto);

        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,id);
        List<DishFlavor> list = dishFlavorService.list(queryWrapper);

        dishDto.setFlavors(list);

        return dishDto;
    }

    @Override
    @Transactional
    public void updateDish(DishDto dishDto) {
        //更新dish表
        this.updateById(dishDto);

        //清楚当前菜品对应的口味数据
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        //添加当前菜品传过来的口味数据
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }
}
