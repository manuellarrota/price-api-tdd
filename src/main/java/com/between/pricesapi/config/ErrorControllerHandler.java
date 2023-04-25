package com.between.pricesapi.config;

import com.between.pricesapi.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ErrorControllerHandler implements ErrorAttributes {
    @Autowired
    private Environment environment;

    @RequestMapping(value = "/error")
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponseDto> runTimeExceptionHandle(RuntimeException e, WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        new Date().toString(),
                        e.getMessage(),
                        e.getStackTrace().toString()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorAttributes.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorAttributes.put("message", "An error occurred");
        errorAttributes.put("path", webRequest.getDescription(false).replace("uri=", ""));
        return errorAttributes;
    }

    @Override
    public Throwable getError(WebRequest webRequest) {
        return null;
    }
}
