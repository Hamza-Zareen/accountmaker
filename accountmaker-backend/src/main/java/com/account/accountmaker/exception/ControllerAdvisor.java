package com.account.accountmaker.exception;

import com.account.accountmaker.enumeration.RestAPIResponseStatus;
import com.account.accountmaker.response.RestAPIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request){
        RestAPIResponse restAPIResponse=new RestAPIResponse();
        restAPIResponse.setMessage(ex.getLocalizedMessage());
        restAPIResponse.setRestAPIResponseStatus(RestAPIResponseStatus.Error);
        restAPIResponse.setResponse(null);
        return ResponseEntity.ok(restAPIResponse);
    }

}
