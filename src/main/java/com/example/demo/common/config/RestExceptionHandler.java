package com.example.demo.common.config;

import com.example.demo.common.exception.UpdateException;
import com.example.demo.entity.Result;
import com.example.demo.common.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/5/20/020.
 */
@ControllerAdvice
@ResponseBody
public class RestExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleResourceNotFoundException(NotFoundException e) {
        logger.error(e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleParamErrorException(ConstraintViolationException e) {
        List<String> errorlist = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        logger.error(errorlist.toString());
        return Result.error(errorlist.toString());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error(e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = UpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleUpdateException(UpdateException e) {
        logger.error(e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleDefaultException(Exception e) {
        logger.error(e.getMessage());
        return Result.error(e.getMessage());
    }

}
