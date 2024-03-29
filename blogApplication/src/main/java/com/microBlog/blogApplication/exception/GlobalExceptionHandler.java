package com.microBlog.blogApplication.exception;

import com.microBlog.blogApplication.payload.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


    @ControllerAdvice
    public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
            ErrorDetail errorDetail=new ErrorDetail(new Date(),exception.getMessage(), webRequest.getDescription(false));
            return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(BlogApplicationException.class)
        public ResponseEntity<ErrorDetail>handleMyBlogAPIException(BlogApplicationException exception, WebRequest webRequest){
            ErrorDetail errorDetail=new ErrorDetail(new Date(),exception.getMessage(), webRequest.getDescription(false));
            return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorDetail>handleGlobalException(Exception exception, WebRequest webRequest){
            ErrorDetail errorDetail=new ErrorDetail(new Date(),exception.getMessage(), webRequest.getDescription(false));
            return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


