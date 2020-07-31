package com.htp.controller;

import com.htp.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.RollbackException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        Pattern  pattern = Pattern.compile(
                "(default)\\p{Blank}(message)\\p{Blank}\\p{Punct}[\\w\\p{Blank}[А-Яа-яЁё\\p{Blank}]*]*\\p{Punct}");
        Matcher matcher = pattern.matcher(e.getMessage());
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        while (matcher.find()){
            String find = matcher.group().replace("default message", "");
            if (count%2==0){
                stringBuilder.append("Validation Failed").append(find);
            }else {
                stringBuilder.append(find).append("; ");
            }
            count++;
        }
        return new ResponseEntity<>(new ErrorMessage(1L, stringBuilder.toString()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> httpMessageNotReadableException(HttpMessageNotReadableException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(2L,"JSON parse error: Unexpected character"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(3L,"Invalid request format"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<ErrorMessage> conversionFailedException(ConversionFailedException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(4L,"Conversion Failed : Resource Not Found"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> dataIntegrityViolationException(DataIntegrityViolationException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(5L,"Data Integrity Violation Exception"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(6L,e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorMessage> handleExpiredJwtException(ExpiredJwtException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(7L,e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(8L, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RollbackException.class)
    public ResponseEntity<ErrorMessage> rollbackException(RollbackException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(9L, "Transaction failed"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<ErrorMessage> jpaSystemException(JpaSystemException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(10L, "Transaction failed"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ErrorMessage> invalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(11L, "Invalid request format"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(12L,e.getLocalizedMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
