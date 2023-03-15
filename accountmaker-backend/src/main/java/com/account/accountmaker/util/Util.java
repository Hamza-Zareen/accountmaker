package com.account.accountmaker.util;

import com.account.accountmaker.enumeration.RestAPIResponseStatus;
import com.account.accountmaker.response.RestAPIResponse;
import org.springframework.http.ResponseEntity;

public class Util {

    public static ResponseEntity<Object> responseEntity(String message, RestAPIResponseStatus restAPIResponseStatus,Object response){
        RestAPIResponse restAPIResponse=new RestAPIResponse();
        restAPIResponse.setMessage(message);
        restAPIResponse.setRestAPIResponseStatus(restAPIResponseStatus);
        restAPIResponse.setResponse(response);
        return ResponseEntity.ok(restAPIResponse);
    }
}
