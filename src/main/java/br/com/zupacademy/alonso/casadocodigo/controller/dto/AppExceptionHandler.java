package br.com.zupacademy.alonso.casadocodigo.controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

        @ExceptionHandler(Exception.class)
        public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
            ErrorMessage error = new ErrorMessage(new Date(),"Server Error", ex.getLocalizedMessage());
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
            ErrorMessage error = new ErrorMessage(new Date(),"Validation Error",ex.getBindingResult().getFieldError().getDefaultMessage());
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }
        
}
