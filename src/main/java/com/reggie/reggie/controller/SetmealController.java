package com.reggie.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.reggie.common.R;
import com.reggie.reggie.dto.SetmealDto;
import com.reggie.reggie.entity.Category;
import com.reggie.reggie.entity.Setmeal;
import com.reggie.reggie.entity.SetmealDish;
import com.reggie.reggie.service.CategoryService;
import com.reggie.reggie.service.SetmeaService;
import com.reggie.reggie.service.SetmealDishService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmeaService setmeaService;
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        setmeaService.saveWithDish(setmealDto);
        return R.success("套餐保存成功");
    }

    @GetMapping("/page")
    public R<Page> pageR(int page, int pageSize, String name) {
        Page<Setmeal> page1 = new Page(page, pageSize);
        Page<SetmealDto> page2 = new Page();
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Setmeal::getName, name);
        setmeaService.page(page1, queryWrapper);
        BeanUtils.copyProperties(page1, page2,"records");
        List<Setmeal> records = page1.getRecords();
        List<SetmealDto> list= records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            Category category = categoryService.getById(item.getCategoryId());
            String name1 = category.getName();
            setmealDto.setCategoryName(name1);
            return setmealDto;
        }).collect(Collectors.toList());
        page2.setRecords(list);

        return R.success(page2);
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        setmeaService.deleteWithDish(ids);
        return R.success("删除成功");

    }
}
