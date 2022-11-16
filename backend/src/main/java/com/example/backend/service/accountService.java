package com.example.backend.service;

import com.example.backend.pojo.User;
import com.example.backend.res.Response;

/**
 * @author Echim9
 * @date 2022/11/16 23:43
 */
public interface accountService {

    public Response register(User user);

    public Response login(User user);
}
