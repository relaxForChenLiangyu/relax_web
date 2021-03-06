package com.ecnu.relax.controller.web;

import com.ecnu.relax.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
    private static final String USER_ID="userId";
    private static final String ADMIN_ID="adminId";

    @Autowired
    protected HttpServletRequest request;

    @ExceptionHandler
    public String handleException(ResourceNotFoundException ex) {
        return "404";
    }

    public int getCurrentUserID(){
        Object o=request.getSession().getAttribute(USER_ID);
        if(o==null){
            throw new ResourceNotFoundException();
        }
        return Integer.parseInt(o.toString());
    }

    public int getCurrentAdminID(){
        Object o=request.getSession().getAttribute(ADMIN_ID);
        if(o==null){
            throw new ResourceNotFoundException();
        }
        return Integer.parseInt(o.toString());
    }
}
