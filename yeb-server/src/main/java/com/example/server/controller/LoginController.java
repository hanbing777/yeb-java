package com.example.server.controller;

import com.example.server.pojo.Admin;
import com.example.server.pojo.AdminLoginParam;
import com.example.server.pojo.RespBean;
import com.example.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @PROJECT_NAME: yeb-java
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/7/29 0029 0:04
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }


    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRolesById(admin.getId()));
        return admin;
    }


    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("注销成功");
    }


}
