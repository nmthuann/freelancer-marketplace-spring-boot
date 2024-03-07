package com.nmt.freelancermarketplacespringboot.common.exceptions.errors;


public class CustomError extends Exception{
    public static final String NOT_FOUND;

    static {
        NOT_FOUND = "Not Found!";
    }

    public static final String PERMISSION_DENIED;

    static {
        PERMISSION_DENIED = "Permission Denied!";
    }

    public static final String NO_SUCCESS;

    static {
        NO_SUCCESS = "Thực hiện tác vụ không thành công.";
    }


}