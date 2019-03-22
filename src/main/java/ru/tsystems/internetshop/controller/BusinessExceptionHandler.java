package ru.tsystems.internetshop.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.tsystems.internetshop.exception.BusinessException;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ModelAndView handleConflict(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");

        modelAndView.addObject("errorMsg", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ModelAndView requestHandlingNoHandlerFound() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");

        modelAndView.addObject("errorMsg", "Page not found");
        return modelAndView;
    }

//    @ExceptionHandler(Exception.class)
//    public ModelAndView qwe() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("exception");
//
//        modelAndView.addObject("errorMsg", "Page not found!EXCEPTION");
//        return modelAndView;
//    }
}
