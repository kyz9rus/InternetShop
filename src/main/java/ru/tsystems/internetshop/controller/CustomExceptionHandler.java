package ru.tsystems.internetshop.controller;

import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.tsystems.internetshop.exception.DAOException;
import ru.tsystems.internetshop.util.ResponseInfo;

/**
 * This class is exception handler, which handles some runtime exception
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * This method handles "page not found"
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView notFoundException() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");
        modelAndView.addObject("responseInfo", new ResponseInfo("Page not found", 404));
        return modelAndView;
    }

    /**
     * This method handles all dao exceptions
     */
    @ExceptionHandler(DAOException.class)
    public ModelAndView daoException() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");
        modelAndView.addObject("responseInfo", new ResponseInfo("An error has occurred", 500));
        return modelAndView;
    }

    /**
     * This method handles all exceptions depends on sending mail
     */
    @ExceptionHandler(MailException.class)
    public ModelAndView mailException() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");
        modelAndView.addObject("responseInfo", new ResponseInfo(500, "An error has occurred<br/>", "REASON:<br/> Check that your email is correct<br/>If it is, write to administrator:<br/> danukrus@yandex.ru"));
        return modelAndView;
    }
}
