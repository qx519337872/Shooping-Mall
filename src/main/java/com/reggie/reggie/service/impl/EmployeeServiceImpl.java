package com.reggie.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie.reggie.entity.Employee;
import com.reggie.reggie.mapper.EmployeeMapper;
import com.reggie.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl  extends ServiceImpl<EmployeeMapper , Employee> implements EmployeeService {
}
