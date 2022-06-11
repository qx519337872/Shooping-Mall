package com.reggie.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie.reggie.common.CustomException;
import com.reggie.reggie.entity.Category;
import com.reggie.reggie.entity.Dish;
import com.reggie.reggie.entity.Setmeal;
import com.reggie.reggie.mapper.CategoryMapper;
import com.reggie.reggie.service.CategoryService;
import com.reggie.reggie.service.DishService;
import com.reggie.reggie.service.SetmeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryImpl  extends ServiceImpl<CategoryMapper, Category > implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmeaService setmeaService;
    /**
     *   //根据id删除分类
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        if(count1>0){
            throw new CustomException("当前分类关联了菜品，不能删除");

        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmeaService.count(setmealLambdaQueryWrapper);
        if(count2>0){
            throw new CustomException("当前分类关联了套餐，不能删除");
        }

        super.removeById(id);






    }
}
