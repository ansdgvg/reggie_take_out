package com.ljx.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljx.entity.SetMeal;
import com.ljx.mapper.SetMealMapper;
import com.ljx.service.SetMealService;
import org.springframework.stereotype.Service;

@Service
public class SetMealServiceImp extends ServiceImpl<SetMealMapper, SetMeal> implements SetMealService {
}
