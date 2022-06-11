package com.reggie.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.reggie.dto.SetmealDto;
import com.reggie.reggie.entity.Setmeal;

import java.util.List;

public interface SetmeaService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);
    void deleteWithDish(List<Long> ids);
}
