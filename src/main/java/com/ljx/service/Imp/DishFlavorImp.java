package com.ljx.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljx.entity.DishFlavor;
import com.ljx.mapper.DishFlavorMapper;
import com.ljx.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorImp extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
