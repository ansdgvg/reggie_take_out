package com.ljx.dto;

import com.ljx.entity.SetMeal;
import com.ljx.entity.SetMealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetMealDto extends SetMeal {

    private List<SetMealDish> setMealDishes;

    private String categoryName;
}
