package com.honbow.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author liuyalong
 */
@RestControllerAdvice
public class AllAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String all(Exception e) {
        String result;
        if (e instanceof NoHandlerFoundException) {
            result = "<h1 align=\"center\">The request path is wrong !</h1>";
        } else {
            result = "<h1 align=\"center\">An unknown error has occurred!</h1><br>" + e.toString();
        }

        return result;
    }
}
