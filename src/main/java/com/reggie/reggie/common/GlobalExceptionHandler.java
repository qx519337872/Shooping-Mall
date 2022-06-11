package com.reggie.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody//返回josn
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public  R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
       if(ex.getMessage().contains("Duplicate entry")){
           String[] split = ex.getMessage().split(" ");
           String msg = split[2] + "已存在";
         return   R.error(msg);
       }
        return  R.error("未知错误");
    }

    @ExceptionHandler(CustomException.class)
    public  R<String> customException(CustomException ex){

        return  R.error(ex.getMessage() );
    }
}
