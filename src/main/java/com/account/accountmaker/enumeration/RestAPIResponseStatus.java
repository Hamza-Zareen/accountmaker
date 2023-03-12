package com.account.accountmaker.enumeration;

public enum RestAPIResponseStatus {
    Unknown(0),
    Success(1),
    Warning(2),
    Error(3);

    private int id;

    RestAPIResponseStatus(int id) { this.id = id; }

    public static RestAPIResponseStatus getById(int id){
        for (RestAPIResponseStatus requestStatus: values()) {
            if(requestStatus.id==id){
                return requestStatus;
            }
        }
        return Unknown;
    }
}
