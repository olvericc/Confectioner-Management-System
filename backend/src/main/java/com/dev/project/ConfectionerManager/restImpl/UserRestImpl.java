package com.dev.project.ConfectionerManager.restImpl;

import com.dev.project.ConfectionerManager.constents.ConfectionerConstants;
import com.dev.project.ConfectionerManager.rest.UserRest;
import com.dev.project.ConfectionerManager.service.UserService;
import com.dev.project.ConfectionerManager.utils.ConfectionerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest
{

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap)
    {
        try
        {
            return userService.signUp(requestMap);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ConfectionerUtils.getResponseEntity(ConfectionerConstants.SOMENTHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}