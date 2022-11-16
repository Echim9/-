package com.example.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author Echim9
 * @date 2022/11/16 23:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userId;

    private String username;

    private String password;
}
