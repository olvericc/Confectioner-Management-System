package com.dev.project.ConfectionerManager.serviceImpl;

import com.dev.project.ConfectionerManager.POJO.User;
import com.dev.project.ConfectionerManager.constents.ConfectionerConstants;
import com.dev.project.ConfectionerManager.dao.UserDao;
import com.dev.project.ConfectionerManager.service.UserService;
import com.dev.project.ConfectionerManager.utils.ConfectionerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside SignUp {}", requestMap);

        try
        {
            if (validateSignUpMap(requestMap))
            {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user))
                {
                    userDao.save(getUserFromMap(requestMap));
                    return ConfectionerUtils.getResponseEntity("Successfully Registered!", HttpStatus.OK);
                }
                else
                {
                    return ConfectionerUtils.getResponseEntity("Email already exists!", HttpStatus.BAD_REQUEST);
                }
            }
            else
            {
                return ConfectionerUtils.getResponseEntity(ConfectionerConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ConfectionerUtils.getResponseEntity(ConfectionerConstants.SOMENTHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap)
    {
        if
        (
            requestMap.containsKey("name") &&
            requestMap.containsKey("contactNumber") &&
            requestMap.containsKey("email") &&
            requestMap.containsKey("password")
        )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private User getUserFromMap(Map<String, String> requestMap)
    {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}