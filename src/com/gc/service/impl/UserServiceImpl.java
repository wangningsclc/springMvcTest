package com.gc.service.impl;

import com.gc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by wn on 2018/6/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello() {
        System.out.println("hi, it's me ");
        return "old friend";
    }
}
