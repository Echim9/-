package com.example.backend.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.enums.Rcode;
import com.example.backend.mapper.userMapper;
import com.example.backend.pojo.User;
import com.example.backend.res.Response;
import com.example.backend.service.accountService;
import com.example.backend.utils.VerifyUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @author Echim9
 * @date 2022/11/16 23:44
 */
public class accountServiceImp implements accountService {

    @Resource
    private com.example.backend.mapper.userMapper userMapper;

    @Override
    public Response register(User user) {
        if (ObjectUtils.isNotEmpty(user)){
            if (StringUtils.isNotEmpty(user.getUsername()) && VerifyUtil.isValidUsername(user.getUsername()) &&
                StringUtils.isNotEmpty(user.getPassword()) && VerifyUtil.isValidPassword(user.getPassword())){
                QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
                userQueryWrapper.eq("username", user.getUsername());
                boolean isExit = ObjectUtils.isEmpty(userMapper.selectOne(userQueryWrapper));
                if (Boolean.TRUE.equals(isExit)){
                    return Response
                            .builder()
                            .code(Rcode.FAIL)
                            .data(user)
                            .msg("用户已存在,注册失败")
                            .build();
                }else {
                    return Response
                            .builder()
                            .code(Rcode.OK)
                            .data(user)
                            .msg("注册成功")
                            .build();
                }
            }else {
                return Response
                        .builder()
                        .code(Rcode.FAIL)
                        .data(user)
                        .msg("用户名或密码不合规")
                        .build();
            }
        }else {
            return new Response<User>()
                    .builder()
                    .data(null)
                    .msg("用户数据异常")
                    .code(Rcode.ERROR)
                    .build();
        }
    }

    @Override
    public Response login(User user) {
        if (ObjectUtils.isNotEmpty(user)){
            if (StringUtils.isNotEmpty(user.getUsername()) && VerifyUtil.isValidUsername(user.getUsername()) &&
                    StringUtils.isNotEmpty(user.getPassword()) && VerifyUtil.isValidPassword(user.getPassword())){
                QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
                userQueryWrapper
                        .eq("username", user.getUsername())
                        .eq("password", user.getPassword());
                boolean isExit = ObjectUtils.isEmpty(userMapper.selectOne(userQueryWrapper));
                if (Boolean.TRUE.equals(isExit)){
                    return Response
                            .builder()
                            .code(Rcode.OK)
                            .data(user)
                            .msg("登陆成功")
                            .build();
                }else {
                    return Response
                            .builder()
                            .code(Rcode.FAIL)
                            .data(user)
                            .msg("用户名或密码错误")
                            .build();
                }
            }else {
                return Response
                        .builder()
                        .code(Rcode.FAIL)
                        .data(user)
                        .msg("用户名或密码不合规")
                        .build();
            }
        }else {
            return new Response<User>()
                    .builder()
                    .data(null)
                    .msg("用户数据异常")
                    .code(Rcode.ERROR)
                    .build();
        }
    }
}
