package com.account.accountmaker.response;

import com.account.accountmaker.enumeration.RestAPIResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestAPIResponse {
    private RestAPIResponseStatus restAPIResponseStatus;
    private String message;
    private Object response;
}
