package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.util.ExceptionMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MyException.class)
    public ModelAndView catchException(MyException me, HandlerMethod hm) {
        String targetView;
        if (hm != null && hm.getMethod().getDeclaringClass().isAnnotationPresent(ControllerViewName.class)) {
            targetView = hm.getMethod().getDeclaringClass().getDeclaredAnnotation(ControllerViewName.class).value();
        } else {
            targetView = "404";
        }

        ModelAndView mv = new ModelAndView(targetView);
        ExceptionMessage excemsg = new ExceptionMessage( me.getStr(),me.getCode());
        mv.addObject("exceptionMessage",excemsg);

       return mv;
    }
}
