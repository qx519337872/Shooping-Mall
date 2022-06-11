package com.reggie.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.reggie.dto.DishDto;
import com.reggie.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    void updaWithFlavor(DishDto dishDto);

}
