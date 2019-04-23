package ru.tsystems.internetshop.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.tsystems.internetshop.exception.DAOException;
import ru.tsystems.internetshop.exception.NewsNotFoundException;
import ru.tsystems.internetshop.util.ResponseInfo;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView notFoundException() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");
        modelAndView.addObject("responseInfo", new ResponseInfo("Page not found", 404));
        return modelAndView;
    }

    @ExceptionHandler(DAOException.class)
    public ModelAndView daoException() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");
        modelAndView.addObject("responseInfo", new ResponseInfo("An error has occurred", 500));
        return modelAndView;
    }

    @ExceptionHandler(NewsNotFoundException.class)
    public ModelAndView newsException() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");
        modelAndView.addObject("responseInfo", new ResponseInfo("The news not found", 404));
        return modelAndView;
    }
}
