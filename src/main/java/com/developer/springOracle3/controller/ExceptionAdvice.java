package com.developer.springOracle3.controller;

import com.developer.springOracle3.MyException;
import com.developer.springOracle3.annotation.ControllerViewName;
import com.developer.springOracle3.util.ExceptionMessage;
import com.developer.springOracle3.util.ExceptionMessage2;
import com.developer.springOracle3.util.MyException2;
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
        ExceptionMessage excemsg = new ExceptionMessage(me.getStr(), me.getCode());
        mv.addObject("exceptionMessage", excemsg);
        return mv;
    }

    @ExceptionHandler(MyException2.class)
    public ModelAndView catchException1(MyException2 me2,HandlerMethod hm)
    {
        String targetViewName;
        if(hm !=null && hm.getMethod().getDeclaringClass().isAnnotationPresent(ControllerViewName.class))
        {
            targetViewName=hm.getMethod().getDeclaringClass().getDeclaredAnnotation(ControllerViewName.class).value();
        }else
        {
            targetViewName="404";
        }
        ModelAndView mv=new ModelAndView(targetViewName);
        ExceptionMessage2 message2=new ExceptionMessage2(me2.getMessage());
        mv.addObject("message2",message2);
        return mv;
    }
}
