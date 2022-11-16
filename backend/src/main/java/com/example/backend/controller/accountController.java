package com.example.backend.controller;

import com.example.backend.pojo.User;
import com.example.backend.res.Response;
import com.example.backend.service.imp.accountServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author Echim9
 * @date 2022/11/16 23:38
 */
@Controller
@RequestMapping("api")
public class accountController {

    @Resource
    private com.example.backend.service.imp.accountServiceImp accountServiceImp;

    @PostMapping("/user/login")
    public Response login(@RequestBody User user) {
        return accountServiceImp.login(user);
    }

    @PostMapping("/user/register")
    public Response register(@RequestBody User user) {
        return accountServiceImp.register(user);
}
}
